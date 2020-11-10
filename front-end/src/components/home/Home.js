import React, {Component} from 'react';
import { Modal } from 'antd';
import Product from '../Product';
import './Home.css';

let myHeaders = new Headers({
  'Access-Control-Allow-Origin': '*',
  'Content-Type': 'application/json',
  'Accept': 'application/json',
});
let api = 'http://localhost:8080/products';
const cartProductApi = 'http://localhost:8080/cartProducts';
const orderApi = 'http://localhost:8080/orders'
class Home extends Component {
  constructor(props) {
    super(props)
    this.state = {
      product: [{ name: '', price: '', unit: '', url: '' }],
      cartProductsList: '',
      visible: false
    }
  }

  getAllOrder = () => {
    fetch(cartProductApi,{
      method:'GET',
      headers: myHeaders,
      mode: 'cors',
    })
    .then((res)=>res.json())
    .then((data)=>{
      const newOrders = this.mergeSameOrder(data)
      this.setState({
        cartProductsList:newOrders,
        visible: true
      })
    })
  }

  mergeSameOrder = (data) => {
    const newOrders = [];
    for (let i = 0; i < data.length; i++) {
      let existSame = false;
      for (let j = 0; j < newOrders.length; j++) {
        if (data[i].name === newOrders[j].name) {
          newOrders[j].count++
          existSame = true
          break
        }
      }
      if(!existSame) {
        newOrders.push(data[i])
      }
    }
    return newOrders
  }

  handleOk = (e) => {
    window.location.pathname = "/orders";
    this.setState({
      visible: false,
    });
    // fetch(orderApi,{
    //   method:'POST',
    //   headers: myHeaders,
    //   mode: 'cors',
    //   body: JSON.stringify({
    //     name:product.name,
    //     price:product.price,
    //     unit:product.unit,
    //     count:1,
    //   })
    // })
  }

  addProduct = (product) => {
    fetch(cartProductApi,{
      method:'POST',
      headers: myHeaders,
      mode: 'cors',
      body: JSON.stringify({
        name:product.name,
        price:product.price,
        unit:product.unit,
        count:1,
      })
    })
    .then(() => this.getAllOrder())
    .then(() => {
      console.log('cartProductsList:', this.state.cartProductsList)
    })
    .catch(() => {})
  }

  reduceProduct = (product) => {
    const reduceProductApi = cartProductApi + `/${product.name}`
    fetch(reduceProductApi,{
      method:'DELETE',
      headers: myHeaders,
      mode: 'cors'
    })
    .then(() => this.getAllOrder())
    .catch(() => {})
  }

  deleteProduct = (product) => {
    const deleteProductApi = cartProductApi + `/${product.name}?allProduct=true`
    fetch(deleteProductApi,{
      method:'DELETE',
      headers: myHeaders,
      mode: 'cors'
    })
    .then(() => this.getAllOrder())
    .catch(() => {})
  }

  deleteAllOrders = () => {
    this.setState({
      visible: false,
    });
    fetch(cartProductApi,{
      method:'DELETE',
      headers: myHeaders,
      mode: 'cors',
    })
    .then((res)=>res.json())
    .then(() => {
      this.setState({
        cartProductsList: '',
      })
    })
    .catch(() => {})
  }

  componentDidMount() {
    fetch(api,{
        method:'GET',
        headers: myHeaders,
        mode: 'cors',
    })
    .then((res)=>res.json())
    .then((data)=>{
      this.setState({
        product:data,
      })
    })
  }

  render() {
    const orders = () => {
      if(this.state.cartProductsList.length === 0) {
        return (<p>暂无商品，请添加商品</p>)
      } else {
        return (
          <table  width="10" height="10" align="center" >
          <thead>
            <tr>
                <th>商品</th>
                <th>数量</th>
            </tr>
          </thead>
          <tbody>
          {this.state.cartProductsList.map((item) => (
            <tr key={item.name}>
              <th>{item.name}</th>
              <th>
                <button onClick={() => this.addProduct(item)}>+</button>
                {item.count}
                <button onClick={() => this.reduceProduct(item)}>-</button>
              </th>
              <th><button onClick={() => this.deleteProduct(item)}>删除</button></th>
            </tr>
          ))}
          </tbody>
        </table>
        )
      }
    }
    return (
      <div className="home">
        <section className='home-body'>
          {this.state.product.map((item) => (
            <Product key={item.name} name={item.name} price={item.price} unit={item.unit} url={item.url} />
          ))}
        </section>
        <button onClick={this.getAllOrder}>购物车</button>
        <Modal
          title="购物车"
          visible={this.state.visible}
          onCancel={this.deleteAllOrders}
          onOk={this.handleOk}
          okText="立即下单"
          cancelText="清空"
        >
          {orders()}
        </Modal>
      </div>
    );
  }
}
export default Home;
