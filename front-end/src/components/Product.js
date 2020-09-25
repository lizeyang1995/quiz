import React from 'react';

class Product extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <section>
        <img className="image" alt="product" src={this.props.url}></img>
        <h2>{this.props.name}</h2>
        <h3>单价{this.props.price}元/{this.props.unit}</h3>
      </section>
    );
  }
}
export default Product;
