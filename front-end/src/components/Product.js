import React from 'react';

let myHeaders = new Headers({
  'Access-Control-Allow-Origin': '*',
  'Content-Type': 'application/json',
  'Accept': 'application/json',
});
const cartProductApi = 'http://localhost:8080/cartProducts';
class Product extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      product:{
        count:0,
      },
      disableButton: ''
    };
  }

  addProduct = () => {
    this.setState({
      disableButton: true
    })
    fetch(cartProductApi,{
      method:'POST',
      headers: myHeaders,
      mode: 'cors',
      body: JSON.stringify({
        name:this.props.name,
        price:this.props.price,
        unit:this.props.unit,
        count:1,
      })
    })
    .then((res)=>res.json())
    .then(()=>{
      this.setState({
        disableButton: ''
      })
    })
    .catch(() => {
      this.setState({
        disableButton: ''
      })
    })
  }

  render() {
    return (
      <section>
        <img className="image" alt="product" src={this.props.url}></img>
        <h3>{this.props.name}</h3>
        <p>单价{this.props.price}元/{this.props.unit}</p>
        <button onClick={this.addProduct} className="add-button" disabled={this.state.disableButton}>+</button>
      </section>
    );
  }
}
export default Product;
