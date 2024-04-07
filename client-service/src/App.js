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
import EditMyBooking from './pages/user/EditMyBooking';

function App() {
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
          <Route exact path='/editbooking' element={<EditMyBooking/>}/>
        </Routes>
    </div>
  );
}

export default App;
