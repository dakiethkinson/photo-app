import React, {useEffect, useRef, useState} from 'react'
import { useNavigate } from 'react-router-dom'
import '../css/output.css'
import {loginUser} from "../api/UserService";
import { toastError } from '../api/ToastService';


const Login = (props) => {
    const modalRef = useRef();
    const fileRef = useRef();
    const [file, setFile] = useState(undefined);

    const [values, setValues] = useState({
        userName: '',
        password: ''
    });

    const onChange = (event) => {
        event.preventDefault();
        setValues({...values, [event.target.name]: event.target.value})
    };

    const handleUserLogin= async (event) => {
        event.preventDefault()
        try {
            const {data} = await loginUser(values);
            const formData = new FormData;
            formData.append('file', file, data.file);
            formData.append('id', data.id);
            toggleModal(false);
            setFile(undefined);
            fileRef.current.value = null;
            setValues({
                userName: '',
                password: ''
            })
        } catch ( err ) {
            console.log(err);
            toastError(err);
        }
    }

    const toggleModal = show => show ? modalRef.current.showModal() : modalRef.current.close();

    return (
        <body className={'loginContainer'}>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'/>
        <div className="loginWrapper">
            <form onSubmit={handleUserLogin}>
                <h1>Login</h1>
                <i onClick={() => toggleModal(false)} className="bi bi-x-lg"></i>
                <div className="input-box">
                    <input type="text" value={values.userName} onChange={onChange} name="userName"
                           placeholder={"Username"} required/>
                    <i className={"bx bxs-user b"}/>
                </div>
                <div className="input-box">
                    <input type="password" value={values.password} onChange={onChange} name="password"
                           placeholder={"Password"} required={true}/>
                    <i className={"bx bxs-lock"}/>
                </div>
                <div className="remember-forgot">
                    <label><input type={"checkbox"}/> Remember me</label>
                    <a href="./resetPassword.js">Forgot password?</a>
                </div>
                <button type={"submit"} className="btn">Login</button>
                <div className="register-link">
                    <p>Don't hava an account?<a href="./signup"> Register</a></p>
                </div>
            </form>
        </div>
        </body>
    )
}

export default Login