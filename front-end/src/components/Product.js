import React from 'react';

class Product extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      product:{
        count:0,
      }
    };
  }

  addProduct = () => {
    this.setState({
      product:{
        name:this.props.name,
        price:this.props.price,
        unit:this.props.unit,
        count:this.state.count + 1
      }
    })
    this.context.router.history.push('/orders', this.state.product);
  }

  render() {
    return (
      <section>
        <img className="image" alt="product" src={this.props.url}></img>
        <h3>{this.props.name}</h3>
        <p>单价{this.props.price}元/{this.props.unit}</p>
        <button onChange={this.addProduct} className="add-button">+</button>
      </section>
    );
  }
}
export default Product;
