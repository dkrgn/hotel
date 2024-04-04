import './App.css';
import Home from './pages/Home';
import { Routes, Route } from 'react-router-dom';
import Login from "./pages/auth/Login";
import Register from "./pages/auth/Register";
import Settings from "./pages/user/Settings";
import MyBooking from "./pages/user/MyBooking";
import EditUser from "./pages/user/EditUser";
import Booking from "./pages/booking/Booking";
import Payment from './pages/payment/Payment';
import axios from 'axios';

function App() {
  if (localStorage.getItem("token_id") !== null) {
    setInterval(async function() {await axios.delete('http://localhost:8080/token/' + localStorage.getItem("token_id"));}, 1000 * 60 * 60);
  }
  return (
    <div className="App">
        <Routes>
          <Route exact path='/' element={<Home/>}/>
          <Route exact path='/login' element={<Login/>}/>
          <Route exact path='/register' element={<Register/>}/>
          <Route exact path='/settings' element={<Settings/>}/>
          <Route exact path='/mybooking' element={<MyBooking/>}/>
          <Route exact path='/booking/:id' element={<Booking/>}/>
          <Route exact path='/payment/:id' element={<Payment/>}/>
          <Route exact path='/edituser' element={<EditUser/>}/>
        </Routes>
    </div>
  );
}

export default App;
