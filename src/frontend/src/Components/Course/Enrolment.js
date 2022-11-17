import React, { useEffect } from "react";
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import { useParams, useNavigate, useLocation, Link } from 'react-router-dom';
import Api from "../../Api";
import Header from "../Home/Header";
import { useState } from 'react';




export default function Enrolment() {

    let [curs, setCurs] = useState({});

    let [enrol,setEnrol]=useState(false);

    let navigate = useNavigate();

    let [message,setMessage]=useState("");

    let { id } = useParams();
   

    useEffect(() => {

        getCourseDetails()

    }, [])


    

    let getCourseDetails = async () => {

        let api = new Api();

        let x = await api.findDasCourseByID(id);

        let stdEnrolled = await api.hasTheEnrollment(1,id);

     
       if(stdEnrolled==true){

        setEnrol(true)
        

       }else{

        setEnrol(false)
        
       }
        setCurs(x);

    }

    let handleEnrolmentCreation = async () => {

        let api = new Api();

        try {

            await api.addAnEnrolment(1, curs);

           setEnrol(true);
          

        } catch (e) {

            if (e) {

              
                setMessage(e.message);
            }

        }

    }

    let handleGoBackHome = () => {

        navigate("/");
    }

    let handleUnenrollment = async ()=>{

        let api = new Api();

        let x = await api.unenrollFromACourse(1,id);

        setEnrol(false);
        
      
    }




    return (
        <>
            <Header />


            <div className="enrolCard">
                <Card style={{ width: '25rem', height: '15rem' }} className="enrol">
                    <Card.Body className="cardBody">
                        <Card.Title>{curs.name}</Card.Title>
                        <Card.Text>
                            Some quick example text to build on the card title and make up the
                            bulk of the card's content.
                        </Card.Text>

                        {
                            enrol
                                ? <>
                                    
                                    <Button variant="primary" className="unenrolBtn" style={{ backgroundColor: "orange" }} onClick={handleUnenrollment}>Unenrol</Button>
                                    <Button variant="primary" className="backBtn" style={{ backgroundColor: "blue" }} onClick={handleGoBackHome}>Back</Button>
                                 
                                </>
                                :
                                <>
                                    <Button variant="primary" className="unenrolBtn" style={{ backgroundColor: "green" }} onClick={handleEnrolmentCreation}>Enrol</Button>
                                    <Button variant="primary" className="backBtn" style={{ backgroundColor: "blue" }} onClick={handleGoBackHome}>Back</Button>

                                </>
                        }
                       
                    </Card.Body>
                </Card>
            </div>


        </>
    )
}