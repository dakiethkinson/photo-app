import React, {useEffect, useRef, useState} from 'react'
import '../css/output.css'
import {getUser, saveUser} from "../api/UserService";
import { toastError } from '../api/ToastService';
import { ToastContainer } from 'react-toastify';
import {redirect} from "react-router-dom";
import {useHistory} from "react-router-dom";

const Signup = (props) => {
    const modalRef = useRef();
    const fileRef = useRef();
    const [data, setData] = useState({});
    const [currentPage, setCurrentPage] = useState(0);
    const [file, setFile] = useState(undefined);
    const [values, setValues] = useState({
        userName: '',
        firstName: '',
        lastName: '',
        email: '',
        password: '',
    });

    const onChange = (event) => {
        event.preventDefault();
        setValues({...values, [event.target.name]: event.target.value})
    };

    const getAllUsers = async (page = 0, size = 10) => {
        try{
            setCurrentPage(page);
            const {data} = await getUser(page, size);
            setData(data);
            console.log(data);
        }   catch(err){
            console.log(err);
            toastError(err);
        }
    }

    const handleNewUser = async (event) => {
        event.preventDefault();
        try{
            const {data} = await saveUser(values);
            // Planned expansion to have user photo icons
            const formData = new FormData;
            formData.append('file', file, data.file);
            formData.append('id', data.id);
            // const { data:photoUrl }
            toggleModal(false);
            setFile(undefined);
            fileRef.current.value = null;
            setValues({
                userName: '',
                firstName: '',
                lastName: '',
                email: '',
                password: '',
            })
            return redirect("/login")
        }   catch (err){
            console.log(err);
            toastError(err);
        }

    }

    const updateUser = async (user) => {
        try{
            const {data} = await saveUser(values);
            console.log(data);
        }   catch (err){
            console.log(err);
            toastError(err);
        }
    }

    const toggleModal = show => show ? modalRef.current.showModal() : modalRef.current.close();

    useEffect(() => {
        getAllUsers();
    }, []);

    return (
        <body className={'loginContainer'}>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'/>
        <div className="loginWrapper">
            <form onSubmit={handleNewUser}>
                <h1>Signup</h1>
                <i onClick={() => toggleModal(false)} className="bi bi-x-lg"></i>
                <div className="input-box">
                    <input type="text" value={values.firstName} onChange={onChange} name='firstName' placeholder={"First Name"} required/>
                </div>
                <div className="input-box">
                    <input type="text" value={values.lastName} onChange={onChange} name='lastName' placeholder={"First Name"} required/>
                </div>
                <div className="input-box">
                    <input type="text" value={values.email} onChange={onChange} name="email" placeholder={"Email Address"} required/>
                </div>
                <div className="input-box">
                    <input type="text" value={values.userName} onChange={onChange} name="userName" placeholder={"Username"} required/>
                </div>
                <div className="input-box">
                    <input type="password" value={values.password} onChange={onChange} name="password" placeholder={"Password"} required={true}/>
                </div>
                <div className="input-box">
                    <input type="password" placeholder="Confirm Password" required={true} onbeforematch={"password"}/>
                </div>
                <button type={"submit"} className="btn">Register</button>
                <div className="register-link">
                    <p>Already hava an account?<a href="./login"> Login</a></p>
                </div>
            </form>
        </div>
        </body>
    )
}

export default Signup