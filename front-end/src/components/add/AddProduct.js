import React, {Component} from 'react';
import './AddProduct.css';

class AddProduct extends Component {
  constructor(props) {
    super(props)
    this.state = {
      name:'',
      price:0,
      unit:'',
      url:''
    }
  }

  render() {
    return (
      <div className='background'>
        <form className='add-product'>
          <h1>添加商品</h1>
          <section className='name'>
            <span>*</span>
            <h3>名称:</h3>
            <input placeholder='名称'></input>
          </section>
          <section className='price'>
            <span>*</span>
            <h3>价格:</h3>
            <input placeholder='价格'></input>
          </section>
          <section className='unit'>
            <span>*</span>
            <h3>单位:</h3>
            <input placeholder='单位'></input>
          </section>
          <section className='price'>
            <span>*</span>
            <h3>图片:</h3>
            <input placeholder='URL'></input>
          </section>
          <button disabled={!this.state.name || !this.state.price || !this.state.unit || !this.state.url}>提交</button>
        </form>
        <footer>TW Mall 2018 Create by ForCheng</footer>
      </div>
    );
  }
}
export default AddProduct;
