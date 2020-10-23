import React, {Component} from 'react';
import { Modal, Button } from 'antd';
import './AddProduct.css';

let myHeaders = new Headers({
  'Access-Control-Allow-Origin': '*',
  'Content-Type': 'application/json',
  'Accept': 'application/json',
});
let api = 'http://localhost:8080/product';
class AddProduct extends Component {
  constructor(props) {
    super(props)
    this.state = {
      name:'',
      price:'',
      unit:'',
      url:'',
      visible: false
    }
  }

  handleChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value
    })
  }

  submit = () => {
    fetch(api,{
      method:'POST',
      headers: myHeaders,
      mode: 'cors',
      body: JSON.stringify({
        name:this.state.name,
        price:this.state.price,
        unit:this.state.unit,
        url:this.state.url,
      })
  })
  .then((res)=>res.json())
  .then((data)=>{
    this.setState({
      visible: data
    })
  })
  }

  handleSubmit = (event) => {
    event.preventDefault();
  }

  handleOk = (e) => {
    this.setState({
      visible: false,
    });
  }

  render() {
    return (
      <div className='background'>
        <form className='add-product' onSubmit={this.handleSubmit}>
          <h1>添加商品</h1>
          <section className='name'>
            <span>*</span>
            <h3>名称:</h3>
            <input placeholder='名称' onChange={this.handleChange} name='name' value={this.state.name}></input>
          </section>
          <section className='price'>
            <span>*</span>
            <h3>价格:</h3>
            <input placeholder='价格' onChange={this.handleChange} name='price' value={this.state.price}></input>
          </section>
          <section className='unit'>
            <span>*</span>
            <h3>单位:</h3>
            <input placeholder='单位' onChange={this.handleChange} name='unit' value={this.state.unit}></input>
          </section>
          <section className='url'>
            <span>*</span>
            <h3>图片:</h3>
            <input placeholder='URL' onChange={this.handleChange} name='url' value={this.state.url}></input>
          </section>
          <button disabled={!this.state.name || !this.state.price || !this.state.unit || !this.state.url || isNaN(this.state.price)} onClick={this.submit}>提交</button>
        </form>
        <footer>TW Mall 2018 Create by ForCheng</footer>

        <Modal
          title="提示"
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleOk}
        >
          <p>商品名称已存在，请输入新的商品名称</p>
        </Modal>

      </div>
    );
  }
}
export default AddProduct;
