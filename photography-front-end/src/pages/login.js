import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import '../css/login.css'

const Login = (props) => {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [emailError, setEmailError] = useState('')
    const [passwordError, setPasswordError] = useState('')

    const navigate = useNavigate()

    const onButtonClick = () => {
        // You'll update this function later...
    }

    return (
        <body>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'/>
        <div className="wrapper">
            <form action="">
                <h1>Login</h1>
                <div className="input-box">
                    <input type="text" placeholder="Username" required={true} />
                    <i className={"bx bxs-user b"}/>
                </div>
                <div className="input-box">
                    <input type="password" placeholder="Password" required={true}/>
                    <i className={"bx bxs-lock"}/>
                </div>
                <div className="remember-forgot">
                    <label><input type={"checkbox"}/> Remember me</label>
                    <a href="./resetPassword.js">Forgot password?</a>
                </div>
                <button type={"submit"} className="btn">Login</button>
                <div className="register-link">
                    <p>Don't hava an account?<a href="./signup.js"> Register</a></p>
                </div>
            </form>
        </div>
        </body>
    )
}

export default Login