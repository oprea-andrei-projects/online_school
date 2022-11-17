import  {useState , useEffect} from "react";
import Header from "./Header";
import Api from "../../Api";
import Main from "./Main";
  



export default function Home(){


    let[courses, setCourses] = useState([]);

    let [message,setMessage]=useState("");
    
    

   

   

    useEffect(()=>{
  
      allCourses();

      
    
     
      // allStudentCourses(8)
      // allTheUserBooks(3)
  
    },[])
  
    let allCourses = async ()=>{
  
      let api = new Api();
  
      let arr = await api.getAllDasCourses();
  
      

      if(typeof arr == "string"){

         setMessage(arr);

      }else{

        setCourses(arr);
      }
   
    }

    let allStudentCourses = async (id)=>{

      let api = new Api();

      let arr = await api.getStudentCourses(id);
      

      setCourses(arr);
    }

  

    

    return(
        <>
          
            <Header />
            <Main courseList={courses} mesaj={message}/>
        
        
        </>
    )
}