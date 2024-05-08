import React, {useState} from 'react';
import '../css/output.css'
import {useHref, useNavigate} from "react-router-dom";

function Header(props) {
    const { loggedIn, email } = props


    return (

        <div >
            <header className="header">
                <a href={"/"} className="logo"><img src={"logo.png"} alt={"LenseTrekker"}/></a>

                <ul className="navbar">
                    <a href={"/"}>Home</a>
                    <a href="#">About</a>
                    <a href="#">Gallery</a>
                    <a href="#">Tutorials</a>
                    <a href="#">Prints</a>
                    <a href="#">Contact</a>
                    <a href={"/login"}>Login</a>

                </ul>

            </header>
        </div>

    );

}

export default Header;