import React, {Component} from 'react';
import {Link} from 'react-router-dom';

class OrderList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      product:[{ name: '', price: '', unit: '', url: '' }],
      noProduct:true,
    };
  }

  render() {
    return (
      <div className="orders">
        <table  width="500" height="50" align="center" 
             cellspacing="5" cellpadding="5">
          <tr>
              <th>名字</th>
              <th>单价</th>
              <th>数量</th>
              <th>单位</th>
              <th>操作</th>
          </tr>
          {this.state.product.map((item) => (
            <tr>
              <th>{item.name}</th>
              <th>{item.price}</th>
              <th>{item.count}</th>
              <th>{item.unit}</th>
              <th><button>删除</button></th>
            </tr>
          ))}
        </table>
        {this.state.noProduct && 
          <p style={{color:'red'}}>暂无订单，返回<Link to='/'>商城页面</Link>继续购买</p>
        }
      </div>
    );
  }
}
export default OrderList;
