import NavBar from "../../layout/NavBar";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { React, useState } from "react";
import "../auth/Auth.css";
import { useNavigate, Link, useLocation } from "react-router-dom";

export default function Booking() {
    const { state } = useLocation();
    const room = state.room;
    const navigate = useNavigate();
    const [startDate, setStartDate] = useState();
    const [endDate, setEndDate] = useState();
    return (
        <div>
            <NavBar/>
            <h1>Choose your preferred dates:</h1>
            <div className="wrapper">
                <div className="room-card border shadow">
                    <div className="room-details">
                        <h3>{room.type}</h3>
                        <p>{room.description}</p>
                        <p>Price: ${room.price} per night</p>
                    </div>
                </div>
                <div>
                        <h5>Choose a start date:</h5>
                        <DatePicker
                        timeZoneOffsetInMinutes={new Date().getTimezoneOffset()}
                        className="border shadow float-left"
                        selectsStart
                        format="yyyy-MM-dd'T'HH:mm:ss.SSSZ"
                        selected={startDate}
                        onChange={(date) => setStartDate(date)}
                        startDate={startDate}
                        minDate={startDate}
                        required
                        />
                    </div>
                    <div>
                        <h5>Choose an end date:</h5>
                        <DatePicker
                            timeZoneOffsetInMinutes={new Date().getTimezoneOffset()}
                            className="float-left border shadow"
                            selectsEnd
                            format="yyyy-MM-dd'T'HH:mm:ss.SSSZ"
                            selected={endDate}
                            onChange={(date) => setEndDate(date)}
                            endDate={endDate}
                            startDate={startDate}
                            minDate={startDate}
                            required
                        />
                    {startDate && endDate &&
                        <div>
                            <Link 
                                    className="shadow border input-box button"
                                    to={`/payment/${room.id}`}
                                    state={{room : room, startDate: startDate, endDate: endDate}}>
                                    Pay Now
                            </Link>
                        </div>
                    }
                    <div>
                        <button className='input-box button mt-3' onClick={() => navigate(-1)}>Back</button>
                    </div>
                </div>
            </div>
        </div>
    )
}