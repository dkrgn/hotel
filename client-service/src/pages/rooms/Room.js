import React from "react";
import "./Room.css";
import { Link } from "react-router-dom";

export default function Room({room}) {
    return (
    <div className="room-card border shadow">
      <div className="room-details">
        <h3>{room.type}</h3>
        <p>{room.description}</p>
        <p>Price: ${room.price} per night</p>
        { localStorage.getItem("token_id") !== null &&
          <Link to={`/booking/${room.id}`}
          state={{room : room}}>
            Book Now
          </Link>
        }
        { localStorage.getItem("token_id") === null &&
          <Link to="/login">
            Book Now
          </Link>
        }
      </div>
    </div>
  );
}