import React,{useState, useMemo, useEffect} from "react";
import { useNavigate, Link } from "react-router-dom"
import Api from "../../Api";

export default function Card({card}){

    let [culoare, setCuloare] = useState(false);

    useEffect(()=>{

        checkEnrollment(1, card.id)

    },[])
    
    let checkEnrollment = async (uid, cid)=>{

        let api = new Api();

        let ceva = await api.hasTheEnrollment(uid,cid);
        
        setCuloare(ceva);
    }

    const themeStyles = useMemo(()=>{


        return{
          
              backgroundColor: culoare ? '#7c689b':'#9bd6b0',
                
              //color: culoare ? 'white':"black"
           
             }
    },[culoare])


    return(
        
        <>

                <div style={{background:themeStyles.backgroundColor}} className="cutie courseName" >

                    {/* <Link to={`/courseDetailsProf/${card.id}`}>{card.name}</Link> */}

                    {/* <Link to={`/courseDetailsStudent/${card.id}`}>{card.name}</Link> */}

                    <Link to={

                        {pathname:`/enrolment/${card.id}`, state:{color:"albastru"}}}>{card.name}</Link>

                    <p className="hDetail">{card.department}</p>

                    {/* <Link
                        to={`/enrolment/${card.id}`}
                        state={{culoare}}>
                       {card.name}
                    </Link> */}

                    {/* <button className="toglebtn" onClick={()=>setColor(prevColor=>!prevColor)}>Change</button> */}

                </div>
        
        
        </>
    )
}