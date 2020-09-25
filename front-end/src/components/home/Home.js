import React, {Component} from 'react';

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
        {this.state.product.map((item) => (
          <div></div>
        ))}
      </div>
    );
  }
}
export default Home;
