import React from 'react';

export default function NavBar() {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                <ul className="nav navbar-nav navbar-left">
                    <li><a className="navbar-brand mx-4" href="/">Hotel Home Page</a></li>
                </ul>
                <ul className="nav navbar-nav ms-auto navbar-right mx-3">
                    <li className="dropdown"><button className="btn btn-outline-light mx-4">Account</button></li>
                    <li className="dropdown"><button className="btn btn-outline-light">Login</button></li>
                </ul>
            </nav>
        </div>
    )
}
