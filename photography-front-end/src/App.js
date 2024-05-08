import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Home from './pages/home'
import Login from './pages/login'
import './css/App.css'
import {useState} from 'react'
import Signup from "./pages/signup";
import Header from "./components/Header"

function App() {
    const [loggedIn, setLoggedIn] = useState(false)
    const [email, setEmail] = useState('')

    return (
      <div className="App">
        <div>
            <Header />
        </div>
        <BrowserRouter>
          <Routes>
              <Route path="/" element={<Home email={email} loggedIn={loggedIn} setLoggedIn={setLoggedIn} />} />
              <Route path="/login" element={<Login setLoggedIn={setLoggedIn} setEmail={setEmail} />} />
              <Route path="/signup" element={<Signup/>} />
          </Routes>
        </BrowserRouter>
      </div>
    )
}

export default App
