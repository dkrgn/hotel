import React, { useEffect, useState } from 'react';
import RoomList from './rooms/RoomList';
import axios from 'axios';
import NavBar from "../layout/NavBar";

export default function Home() {
    const[rooms, setRooms] = useState([]);
    useEffect(() => {
        loadRooms();
    },[]);
    const loadRooms = async() => {
        await axios.get("http://localhost:8080/room-api/available")
        .then(res => {
            console.log(res.data);
            setRooms(res.data);
        })
        .catch(e => {
            console.log(e);
        });
    }
    return (
        <div>
            <NavBar/>
            <div>
                <h1>Welcome to Our Hotel</h1>
                <RoomList rooms={rooms} />
            </div>
        </div>
    )
}