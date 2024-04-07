import React, { useState } from "react";
import '../auth/Auth.css';
import { useNavigate, useLocation } from "react-router-dom";
import DatePicker from "react-datepicker";
import NavBar from "../../layout/NavBar";
import axios from 'axios';

export default function EditMyBooking() {
    const { state } = useLocation();
    const navigate = useNavigate();
    const [startDate, setStartDate] = useState();
    const [endDate, setEndDate] = useState();
    const data = state.e.bookingResponse;
    const handleEdit = async() => {
        const start = startDate === null ? data.start : startDate;
        const end = endDate === null ? data.end : endDate;
        const userId = data.userId;
        const roomId = data.roomId;
        const paymentId = data.paymentId;
        const request = { 
            userId: userId,
            roomId: roomId,
            paymentId: paymentId,
            start : start, 
            end : end
        };
        axios.put('http://localhost:8080/booking-api/' + data.id, request)
        .then(response => {
            let data = response.data;
            console.log(data);
            if (data !== null) {
                navigate("/mybooking");
            }
        })
        .catch(err => {
            console.log(err);
            alert("Could not update booking! Error occurred!");
            navigate("/mybooking");
        })
    }

    return (
        <div>
            <NavBar/>
            <h1>EditMyBooking</h1>
            <div className="wrapper">
                <div>
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
                    </div>
                    <div>
                         <button className='input-box button mt-3' onClick={handleEdit}>Edit Booking</button>
                    </div>
                    <div>
                        <button className='input-box button mt-3' onClick={() => navigate(-1)}>Back</button>
                    </div>
                </div>
            </div>
        </div>
    )
}