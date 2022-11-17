import React from "react"

import { useNavigate , Link} from "react-router-dom"

export default function Header(){

    let id = 1;

    let navigate = useNavigate();

    let handleClick = ()=>{

        navigate("/");
        
    }

   


    return(


        <>
            <nav className="navbar navbar-expand-lg ">
                <div className="container-fluid">
                    <h1 className="bigTitle" onClick={handleClick}>Courses</h1>
                   
                    <Link className="carti" to={`/bookLib/${id}`}>MyBooks</Link>
                    <p className="welcome">Welcome Andrei !</p>
                    <p className="signOut">Sign out</p>
                </div>
            </nav>

            
        
        </>
    )
}