import React from 'react'
import { useNavigate } from 'react-router-dom'
import "../css/output.css"

const Home = (props) => {
    const { loggedIn, email } = props
    const navigate = useNavigate()

    const onButtonClick = () => {
        navigate("/login")
    }

    return (
        <body className={'mainContainer'}>
        <div>
            <h1><img src={'lensetrekker.png'} alt={"LenseTrekker"} className={"bannerImage"}/></h1>
        </div>
        <div className="introBox">sdfg</div>
        </body>
    )
}

export default Home