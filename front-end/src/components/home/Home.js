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
      orderList: ''
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
      this.setState({
        orderList:data,
      })
    })
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
    return (
      <div className="home">
        <section className='home-body'>
          {this.state.product.map((item) => (
            <Product key={item.name} name={item.name} price={item.price} unit={item.unit} url={item.url} />
          ))}
        </section>
        <button onClick={}>购物车</button>
        <Modal
          title="Basic Modal"
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleOk}
        >
          <p>dev中</p>
        </Modal>
      </div>
    );
  }
}
export default Home;
