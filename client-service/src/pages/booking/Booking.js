import React from "react";
import { useLocation } from "react-router-dom";
import NavBar from "../../layout/NavBar";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { useState } from "react";
import "../../pages/rooms/Room.css";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

export default function Booking() {
    const { state } = useLocation();
    const room = state.room;
    const navigate = useNavigate();
    const [startDate, setStartDate] = useState();
    const [endDate, setEndDate] = useState();
    return (
        <div>
            <NavBar/>
            <div className="d-flex align-items-center justify-content-center">
                <div className="room-card border shadow">
                    <div className="room-details">
                        <h3>{room.type}</h3>
                        <p>{room.description}</p>
                        <p>Price: ${room.price} per night</p>
                    </div>
                </div>
                <div>
                    <DatePicker
                    className="border shadow"
                    selectsStart
                    format="yyyy-MM-dd'T'HH:mm:ss.SSS"
                    selected={startDate}
                    onChange={(date) => setStartDate(date)}
                    startDate={startDate}
                    minDate={startDate}
                    required
                    />
                    <br/>
                    <DatePicker
                        className="border shadow"
                        selectsEnd
                        format="yyyy-MM-dd'T'HH:mm:ss.SSS"
                        selected={endDate}
                        onChange={(date) => setEndDate(date)}
                        endDate={endDate}
                        startDate={startDate}
                        minDate={startDate}
                        required
                    />
                    <br/>
                    {startDate && endDate &&
                        <Link 
                                className="shadow border"
                                to={`/payment/${room.id}`}
                                state={{room : room, startDate: startDate, endDate: endDate}}>
                                Pay Now
                        </Link>
                    }
                    <div className='input-box button'>
                        <button onClick={() => navigate(-1)}>Back</button>
                    </div>
                </div>
            </div>
        </div>
    )
}