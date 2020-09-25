import React, {Component} from 'react';
import Product from '../Product';
import './Home.css';

let myHeaders = new Headers({
  'Access-Control-Allow-Origin': '*',
  'Content-Type': 'text/plain'
});
let api = 'http://localhost:8080/products';
class Home extends Component {
  constructor(props) {
    super(props)
    this.state = {
      product: [{ name: '', price: '', unit: '', url: '' }],
    }
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
      </div>
    );
  }
}
export default Home;
