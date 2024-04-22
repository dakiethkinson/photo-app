import React from "react";
import {Link} from "react-router-dom";

const User = ({user}) => {
    return (
        <Link to="/">
            <div className="user_header">
                <div className="user_image">
                    <img src={} alt={user.userName}/>
                </div>
                <div className="user_details">
                    <p>{user.userName}</p>
                    <p>{user.email}</p>
                    <p>{user.firstName}</p>
                    <p>{user.lastName}</p>
                </div>
            </div>
        </Link>
    )
}