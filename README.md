<h1>copeCode</h1>

```
F:.                              
├───.idea                        
│   └───libraries                
├───.mvn                         
│   └───wrapper                  
├───data                         
│   └───images                   
│       ├───admin                
│       ├───officer
│       ├───reportImage
│       └───user
├───src
│   └───main
│       ├───java
│       │   └───ku
│       │       └───cs
│       │           ├───app
│       │           │   ├───controllers
│       │           │   │   ├───accountOrganizer
│       │           │   │   ├───howTo
│       │           │   │   └───mainPage
│       │           │   ├───models
│       │           │   │   └───list
│       │           │   └───services
│       │           └───com
│       │               └───github
│       │                   └───saacsos
│       │                       └───fxrouter-1.0.0
│       │                           └───src
│       │                               └───main
│       │                                   ├───java
│       │                                   │   ├───com
│       │                                   │   │   └───github
│       │                                   │   │       └───saacsos
│       │                                   │   └───sample
│       │                                   │       └───controllers
│       │                                   └───resources
│       └───resources
│           ├───cssStyle
│           └───ku
│               └───cs
│                   ├───defaultImage
│                   └───images
└───target
├───classes
│   ├───com
│   │   └───github
│   │       └───saacsos
│   ├───cssStyle
│   ├───ku
│   │   └───cs
│   │       ├───app
│   │       │   ├───controllers
│   │       │   │   ├───accountOrganizer
│   │       │   │   ├───howTo
│   │       │   │   └───mainPage
│   │       │   ├───models
│   │       │   │   └───list
│   │       │   └───services
│   │       ├───defaultImage
│   │       └───images
│   └───sample
│       └───controllers
├───data
│   └───images
│       ├───admin
│       ├───officer
│       ├───reportImage
│       └───user
├───generated-sources
│   └───annotations
├───maven-archiver
└───maven-status
└───maven-compiler-plugin
└───compile
└───default-compile
```
```
README.md                          
data                               
   |-- category.csv                
   |-- images                      
   |   |-- admin
   |   |   |-- default.jpg               
   |   |-- officer                 
   |   |   |-- default.jpg         
   |   |-- reportImage
   |   |-- user
   |   |   |-- default.jpg
   |-- inappropriateUser.csv
   |-- report.csv
   |-- suspendedUser.csv
   |-- user.csv
   |-- userRequest.csv
src
   |-- main
   |   |-- java
   |   |   |-- ku
   |   |   |   |-- cs
   |   |   |   |   |-- Main.java
   |   |   |   |   |-- ProjectApplication.java
   |   |   |   |   |-- ProjectController.java
   |   |   |   |   |-- app
   |   |   |   |   |   |-- controllers
   |   |   |   |   |   |   |-- AssignReportFormController.java
   |   |   |   |   |   |   |-- CategoryEditorController.java
   |   |   |   |   |   |   |-- CreditController.java
   |   |   |   |   |   |   |-- MyReportFormController.java
   |   |   |   |   |   |   |-- ProfileFormController.java
   |   |   |   |   |   |   |-- ReportInappropriateActivityFormController.java
   |   |   |   |   |   |   |-- SuspendedUserController.java
   |   |   |   |   |   |   |-- accountOrganizer
   |   |   |   |   |   |   |   |-- AddOfficerFormController.java
   |   |   |   |   |   |   |   |-- LogDataFormController.java
   |   |   |   |   |   |   |   |-- LoginFormController.java
   |   |   |   |   |   |   |   |-- RegisterFormController.java
   |   |   |   |   |   |   |   |-- RequestFormController.java
   |   |   |   |   |   |   |   |-- ResetPasswordFormController.java
   |   |   |   |   |   |   |   |-- UserDataListController.java
   |   |   |   |   |   |   |   |-- UserSuspensionFormController.java
   |   |   |   |   |   |   |-- howTo
   |   |   |   |   |   |   |   |-- AdminHowToController.java
   |   |   |   |   |   |   |   |-- HowToProfileController.java
   |   |   |   |   |   |   |   |-- OfficerHowToController.java
   |   |   |   |   |   |   |   |-- UserHowToAssignController.java
   |   |   |   |   |   |   |   |-- UserHowtoController.java
   |   |   |   |   |   |   |-- mainPage
   |   |   |   |   |   |   |   |-- MainAdminFormController.java
   |   |   |   |   |   |   |   |-- MainOfficerFormController.java
   |   |   |   |   |   |   |   |-- MainUserFormController.java
   |   |   |   |   |   |-- models
   |   |   |   |   |   |   |-- Activity.java
   |   |   |   |   |   |   |-- Admin.java
   |   |   |   |   |   |   |-- InappropriateUser.java
   |   |   |   |   |   |   |-- Officer.java
   |   |   |   |   |   |   |-- Password.java
   |   |   |   |   |   |   |-- Report.java
   |   |   |   |   |   |   |-- User.java
   |   |   |   |   |   |   |-- UserRequest.java
   |   |   |   |   |   |   |-- UserSuspension.java
   |   |   |   |   |   |   |-- list
   |   |   |   |   |   |   |   |-- ActivityLog.java
   |   |   |   |   |   |   |   |-- CheckIfExistAndReturnObject.java
   |   |   |   |   |   |   |   |-- DynamicCategory.java
   |   |   |   |   |   |   |   |-- InappropriateUserList.java
   |   |   |   |   |   |   |   |-- ReportList.java
   |   |   |   |   |   |   |   |-- UserList.java
   |   |   |   |   |   |   |   |-- UserRequestList.java
   |   |   |   |   |   |   |   |-- UserSuspensionList.java
   |   |   |   |   |   |-- services
   |   |   |   |   |   |   |-- ActivityLogDataSource.java
   |   |   |   |   |   |   |-- DataSource.java
   |   |   |   |   |   |   |-- DefaultCategoryHardCode.java
   |   |   |   |   |   |   |-- DefaultImageHardCode.java
   |   |   |   |   |   |   |-- DefaultUserHardCode.java
   |   |   |   |   |   |   |-- DynamicCategoryFileSource.java
   |   |   |   |   |   |   |-- ImageDataSource.java
   |   |   |   |   |   |   |-- InappropriateUserListFileDataSource.java
   |   |   |   |   |   |   |-- ReportImageDataSource.java
   |   |   |   |   |   |   |-- ReportListFileDataSource.java
   |   |   |   |   |   |   |-- Sorter.java
   |   |   |   |   |   |   |-- UserDataListFileDataSource.java
   |   |   |   |   |   |   |-- UserRequestListFileDataSource.java
   |   |   |   |   |   |   |-- UserSuspensionListFileSource.java
   |   |   |   |   |-- com
   |   |   |   |   |   |-- github
   |   |   |   |   |   |   |-- saacsos
   |   |   |   |   |   |   |   |-- fxrouter-1.0.0
   |   |   |   |   |   |   |   |   |-- .gitignore
   |   |   |   |   |   |   |   |   |-- LICENSE
   |   |   |   |   |   |   |   |   |-- README.md
   |   |   |   |   |   |   |   |   |-- pom.xml
   |   |   |   |   |   |   |   |   |-- src
   |   |   |   |   |   |   |   |   |   |-- main
   |   |   |   |   |   |   |   |   |   |   |-- java
   |   |   |   |   |   |   |   |   |   |   |   |-- com
   |   |   |   |   |   |   |   |   |   |   |   |   |-- github
   |   |   |   |   |   |   |   |   |   |   |   |   |   |-- saacsos
   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |-- FXRouter.java
   |   |   |   |   |   |   |   |   |   |   |   |-- sample
   |   |   |   |   |   |   |   |   |   |   |   |   |-- Main.java
   |   |   |   |   |   |   |   |   |   |   |   |   |-- controllers
   |   |   |   |   |   |   |   |   |   |   |   |   |   |-- FirstPageController.java
   |   |   |   |   |   |   |   |   |   |   |   |   |   |-- SecondPageController.java
   |   |   |   |   |   |   |   |   |   |   |   |   |   |-- ThirdPageController.java
   |   |   |   |   |   |   |   |   |   |   |-- resources
   |   |   |   |   |   |   |   |   |   |   |   |-- first.fxml
   |   |   |   |   |   |   |   |   |   |   |   |-- second.fxml
   |   |   |   |   |   |   |   |   |   |   |   |-- third.fxml
   |   |   |-- module-info.java
   |   |-- resources
   |   |   |-- cssStyle
   |   |   |   |-- style.css
   |   |   |-- ku
   |   |   |   |-- cs
   |   |   |   |   |-- activityLog.fxml
   |   |   |   |   |-- addOfficerForm.fxml
   |   |   |   |   |-- adminHowToForm.fxml
   |   |   |   |   |-- assignReportForm.fxml
   |   |   |   |   |-- categoryEditor.fxml
   |   |   |   |   |-- credit.fxml
   |   |   |   |   |-- defaultImage
   |   |   |   |   |   |-- default.jpg
   |   |   |   |   |-- howToProfile.fxml
   |   |   |   |   |-- images
   |   |   |   |   |   |-- actLog.png
   |   |   |   |   |   |-- adminHome.png
   |   |   |   |   |   |-- adminHowTo.png
   |   |   |   |   |   |-- agentHome.png
   |   |   |   |   |   |-- assign.png
   |   |   |   |   |   |-- cope.png
   |   |   |   |   |   |-- credit.png
   |   |   |   |   |   |-- default.jpg
   |   |   |   |   |   |-- howToAssign.png
   |   |   |   |   |   |-- howtoProfile.png
   |   |   |   |   |   |-- myReport.png
   |   |   |   |   |   |-- newOfficer.png
   |   |   |   |   |   |-- officerHowTo.png
   |   |   |   |   |   |-- profile.png
   |   |   |   |   |   |-- register.png
   |   |   |   |   |   |-- reportUser.png
   |   |   |   |   |   |-- request.png
   |   |   |   |   |   |-- reset.png
   |   |   |   |   |   |-- suspensionManager.png
   |   |   |   |   |   |-- userHome.png
   |   |   |   |   |   |-- userHowTo.png
   |   |   |   |   |   |-- userInfo.png
   |   |   |   |   |-- loginForm.fxml
   |   |   |   |   |-- loginForm.fxml
   ```
----------------------------------------------------------------<br>

<h3>วิธีการติดตั้ง</h3>
1.ดาวน์โหลดไฟล์ชื่อว่า copeCodeSetup.zip ไว้ที่ตำแหน่งที่ต้องการ<br>
2.ทำการแตกไฟล์ copeCodeSetup.zip ออก<br>
3.เมื่อแตกไฟล์ออกให้ดับเบิ้ลคลิกที่ไฟล์ copeCode.jar เพื่อเริ่มใช้งานโปรแกรม<br>

----------------------------------------------------------------<br>
----------------------------------------------------------------<br>


<h3>รหัสผู้ใช้งานตัวอย่าง</h3>
- Admin<br>
Username: Tester303<br>
Password: Tester12345<br><br>
- Officer<br>
Username: Tester202<br> Password: Tester12345<br><br>
- User<br>
Username: Tester101<br> Password: Tester12345<br><br>

----------------------------------------------------------------<br>
----------------------------------------------------------------<br>

<h3>UPDATE LAST PATCH</h3>

<h4>หน้าต่าง</h4>
-หน้าต่างแสดงการเข้าใช้งานระบบ<br>
-หน้าวิธีใช้ภายในระบบในทุกๆหน้า<br>
-หน้าเรื่องร้องเรียนของผู้ใช้ที่ใช้งานอยู่<br>
-เพิ่มหน้าต่างหลักของเจ้าหน้าที่<br>
-ปรับแต่งหน้าต่างในแต่ละส่วนใหม่<br>

<h4>ระบบ</h4>
-เพิ่มปุ่มให้ vote ให้คะแนนกับเรื่องร้องเรียนนั้นๆ<br>
-การแจ้งเรื่องร้องเรียนสามารถใส่รูปภาพได้<br>
-การแยกเรื่องร้องเรียนที่ถูกแก้ไขแล้วและที่ดำเนินการอยู่<br>
-การเรียงเรื่องร้องเรียนตามหมวดหมู่, ตามเก่าใหม่, ตามคะแนนมากน้อยของเรื่องร้องเรียน<br>
-การเรียงเรื่องร้องเรียนตามคะแนนที่มากกว่าจำนวนที่ต้องการ<br>
-การเพิ่มและลบหมวดหมู่ออกได้<br>

----------------------------------------------------------------<br>
<h3>UPDATE THIRD PATCH</h3>
<h4>หน้าต่าง</h4>
-หน้าจัดการระงับการใช้งานผู้ใช้<br>
-หน้ารวมเรื่องร้องเรียนมีการแสดงรายละเอียดข้อมูล<br>

<h4>ระบบ</h4>
-เพิ่มการสร้างเจ้าหน้าที่ผ่านทางหน้าของผู้ดูแลระบบ<br>
-เช็คชื่อผู้ใช้ที่มีซ้ำกันไม่ให้ถูกสร้างขึ้น<br>
----------------------------------------------------------------<br>
<h3>UPDATE SECOND PATCH</h3>
<h4>หน้าต่าง</h4>
-หน้าโปรไฟล์<br>
-หน้าแจ้งเรื่องร้องเรียน<br>

<h4>ระบบ</h4>
-เพิ่มการอ่านเขียนข้อมูลลง และดึงข้อมูลผ่านไฟล์ csv<br>
-เพิ่มระบบการเปลี่ยนรหัสผ่าน<br>
-มีการแสดงข้อมูลเรื่องร้องเรียนออกมาทาง UI<br>
-ระบบการเข้าใช้งาน(login) ผ่านการเช็ค csv<br>
-ระบบการสร้างรหัสผ่าน<br>
----------------------------------------------------------------<br>

<h3>UPDATE FIRST PATCH</h3>
<h4>หน้าต่าง</h4>
-เพิ่มหน้าหลัก<br>
-เพิ่มหน้าสมัครสมาชิก<br>
-เพิ่มหน้าเข้าสู่ระบบ<br>
-เพิ่มหน้าเปลี่ยนรหัสผ่าน<br>
-เพิ่มหน้ารายชื่อผู้ใช้ (พัฒนาให้ผู้ดูแลระบบ)<br>
-เพิ่มหน้าผู้จัดทำ<br>

<h4>ระบบ</h4>
-เพิ่มระบบการตรวจสอบรหัสผ่าน ทั้งการเข้าใช้งานและการเปลี่ยนรหัส<br>
-เพิ่มการแสดงผลรายชื่อและรายละเอียดข้อมูลของผู้ใช้<br>
----------------------------------------------------------------<br>
----------------------------------------------------------------<br>
CS211 - Project<br>
วิธีทดสอบการ RUN<br>
1. Main <br>
run Main Class
2. javafx plugin<br>
MVN Clean<br>
javafx -> javafx:run<br><br>

วิธีสร้าง Jar<br>
MVN Clean<br>
MVN install<br><br>
file จะอยู่ใน target เป็น shade.jar 




