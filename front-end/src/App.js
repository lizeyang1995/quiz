import React from 'react';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import './App.css';
import Header from './components/Header';
import Home from './components/home/Home';
import OrderList from './components/orderList/OrderList';
import AddProduct from './components/add/AddProduct';

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        <Switch>
          <Route exact path='/' component={Home} />
          <Route exact path='/orders' component={OrderList} />
          <Route exact path='/product' component={AddProduct} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
