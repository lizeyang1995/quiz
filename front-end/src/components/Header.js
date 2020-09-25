import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import './Header.css';

class Header extends Component {
  render() {
    return (
      <div className="header">
        <p>
          <Link className='link' to='/'>商城</Link>
        </p>
        <p>
          <Link className='link' to='/orders'>订单</Link>
        </p>
        <p>
          <Link className='link' to='/product'>添加商品</Link>
        </p>
      </div>
    )
  }
}
export default Header;
