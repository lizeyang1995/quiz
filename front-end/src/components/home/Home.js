import React, {Component} from 'react';

class Home extends Component {
  constructor(props) {
    super(props)
    this.state = {
      product: [{ name: '', price: '', unit: '', url: '' }],
    }
  }

  render() {
    return (
      <div className="home">
        
      </div>
    );
  }
}
export default Home;
