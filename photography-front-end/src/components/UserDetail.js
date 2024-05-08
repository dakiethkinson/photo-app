import React, { useState, useEffect, useRef } from "react";
import {Link, useParams} from 'react-router-dom';
import {getUser, getUsers} from "../api/UserService";
import { toastError, toastSuccess } from "../api/ToastService";

const UserDetail = ({updateUser, UpdateImage}) => {
    const inputRef = useRef();
    const [user, setUser] = useState({

        userName: '',
        email: '',
        password: '',
        firstName: '',
        lastName: '',
    });

    const { id } = useParams();

    const fetchUser = async (id) => {
        try{
            const {data} = await getUser(id)
            setUser(data);
            console.log(data);
        } catch (error) {
            console.log(error);
            toastError(error.message);
        }
    };

    const onChange = (event) => {
        setUser(({...user, [event.target.name]: event.target.value}));
    };
    
    const onUpdateUser = async (event) => {
        event.preventDefault();
        await updateUser(user);
        fetchUser(id);
        toastSuccess('User updated successfully.');
    };
    
    useEffect(() => {
        fetchUser(id);
    }, []);
    
    return (
        <Link to={''}></Link>
    )
}