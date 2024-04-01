import React from "react";
import "./Room.css";

export default function Room({room}) {
    return (
    <div className="room-card border shadow">
      {/* <img src={room.image} alt={room.name} /> */}
      <div className="room-details">
        <h3>{room.type}</h3>
        <p>{room.description}</p>
        <p>Price: ${room.price} per night</p>
        <button>Book Now</button>
      </div>
    </div>
  );
}