import React, {Component} from 'react';
import { Modal } from 'antd';
import Product from '../Product';
import './Home.css';

let myHeaders = new Headers({
  'Access-Control-Allow-Origin': '*',
  'Content-Type': 'text/plain'
});
let api = 'http://localhost:8080/products';
let getAllOrderApi = 'http://localhost:8080/orders';
class Home extends Component {
  constructor(props) {
    super(props)
    this.state = {
      product: [{ name: '', price: '', unit: '', url: '' }],
      orderList: '',
      visible: false
    }
  }

  getAllOrder = () => {
    fetch(getAllOrderApi,{
      method:'GET',
      headers: myHeaders,
      mode: 'cors',
    })
    .then((res)=>res.json())
    .then((data)=>{
      console.log(data)
      this.setState({
        orderList:data,
        visible: true
      })
    })
  }

  handleOk = (e) => {
    this.setState({
      visible: false,
    });
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
      if(this.state.orderList.length === 0) {
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
          {this.state.orderList.map((item) => (
            <tr key={item.name}>
              <th>{item.name}</th>
              <th>{item.count}</th>
              <th><button>删除</button></th>
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
          onCancel={this.handleOk}
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
