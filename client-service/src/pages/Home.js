import React, { useEffect, useState } from 'react';
import RoomList from './rooms/RoomList';
import axios from 'axios';
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';

export default function Home() {
    const[rooms, setRooms] = useState([]);
    useEffect(() => {
        loadRooms();
    });
    const loadRooms = async() => {
        const response = await axios.get("http://localhost:8080/room-api/all");
        setRooms(response.data);
    }
    return (
        <div className='container'>
            <div className='py-4'>
                <h1>Welcome to Our Hotel</h1>
                <RoomList rooms={rooms} />
            </div>
        </div>
    )
}