import React, { useEffect, useState } from 'react';
import RoomList from './rooms/RoomList';
import axios from 'axios';
import NavBar from "../layout/NavBar";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Booking from './booking/Booking';

export default function Home() {
    const[rooms, setRooms] = useState([]);
    useEffect(() => {
        loadRooms();
    },[]);
    const loadRooms = async() => {
        const response = await axios.get("http://localhost:8080/room-api/all");
        setRooms(response.data);
    }
    return (
        <div>
            <NavBar/>
            <div>
                <h1>Welcome to Our Hotel</h1>
                <RoomList rooms={rooms} />
            </div>
            <Router>
                <Routes>
                    <Route exact path='/booking' element={<Booking rooms={rooms}/>}/>
                </Routes>
            </Router>
        </div>
    )
}