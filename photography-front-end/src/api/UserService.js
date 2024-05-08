import axios from "axios";
import {redirect, useNavigate} from "react-router-dom";

const baseURL = "http://localhost:8082";
const userPath = "/users"


export async function getUsers() {
    return await axios.get(baseURL);
}

export async function getUser(id) {
    return await axios.get(`${baseURL}${userPath}/${id}`);
}

export async function saveUser(user) {
    return await axios
        .post(`${baseURL}${userPath}/signup`)
        .then(function (response) {
        });
}

export async function updateUser(id, user) {
    return await axios.put(`${baseURL}${userPath}/${id}`, user);
}

export async function deleteUser(id) {
    return await axios.delete(`${baseURL}${userPath}/${id}`);
}

export async function loginUser(user) {
    return await axios
        .post(`${baseURL}${userPath}/login`, user)
        .then(r => {
            if (r.status === 200) {
                redirect("/")
            }
        });
}