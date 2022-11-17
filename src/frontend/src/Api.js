
import fetch from 'unfetch'
export default class Api{



    api(path, method='GET', body=null){
 
        const url = "/" + path;

        const options = {

                method,
                
                headers:{

                    'Content-Type':'application/json; charset=utf-8',
                    'X-Requested-With': 'XMLHttpRequest'


                }

        };

        if(body!==null){
            options.body = JSON.stringify(body);
        }

        
        return fetch(url, options);
   }

   async getAllDasCourses (){
      try{
        let x = await this.api(`api/v1/allCourses`);
        let y = await x.json();
        if(x.status == 200){
          return y;
        }else if(x.status ==400){
          return y.message;
        }
      }catch(e){
        throw new Error(e);
      }
   }

   async addACourse(course){

        return this.api(`api/v1/addACourse`,`POST`,course).then(data=>data.json());
   }

   async findDasCourseByID(id){

        let x = await this.api(`api/v1/findCourseByID/${id}`,`GET`);

        let y = await x.json();

        return y;
   } 

   async addAnEnrolment(id, course){

     
     let x = await this.api(`api/v1/placeEnrolment/?id=${id}`,`POST`,course);
     if(x.status==200){
          return x.json();
     }else{

           let  data= await x.json();
          console.log(data);
          throw new Error(data.message);
     }

   }

   async deleteDasCourse(id){

        let x = await this.api(`api/v1/deleteCourse/?id=${id}`,`DELETE`);

        let y = await x.json();
        
      

        
   }

   async unenrollFromACourse(uid, cid){

          let x = await this.api(`api/v1/unenroll/?uid=${uid}&cid=${cid}`,`DELETE`);

          let y = await x.json();

   }

   async getStudentCourses(id){

     return this.api(`api/v1/getUserCourses/${id}`,`GET`).then(x=>x.json());
   }

   async getUserBooks(id){

    try{

      let x = await this.api(`api/v1/getUserBooks/${id}`,`GET`);

      console.log(x);
      let y = await x.json();
 
       if(x.status == 200){
 
         return y;
 
       }else if(x.status == 400){
 
         return y.message;
 
       }

    }catch(e){

      return new Error(e);
    }

    

    

   }

   async hasTheEnrollment(uid,cid){

     let x = await this.api(`api/v1/studentHasEnrollment?uid=${uid}&cid=${cid}`,`GET`);
     let y = await x.json();
   
     return y;
   }

   async addDasBook(id, book){

     let x = await this.api(`api/v1/addBook?id=${id}`,`POST`,book);
     let y = await x.json();
     console.log(y);
     return y;
   }

   async deleteDasBook(uid, bid){

     let x = await this.api(`api/v1/deleteBook?uid=${uid}&bid=${bid}`,`DELETE`);
     let y = await x.json();
   }

   async updateDasBook(uid, book){

      let x = await this.api(`api/v1/updateBook?uid=${uid}`,`PUT`,book);
      let y = await x.json();
      return y;

   }

   async updateTheCourse(uid, course){
    let x = await this.api(`api/v1/updateCourse?uid=${uid}`,`PUT`,course);
    let y = await x.json();
    return y;

   }



}