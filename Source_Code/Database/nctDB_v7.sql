/* Drop Tables */

DROP TABLE TestResults;
DROP TABLE Booking;
DROP TABLE Car;
DROP TABLE Users;
DROP TABLE UserType;
DROP TABLE Garage;
DROP TABLE Owner;

/* Create Tables */

CREATE TABLE Owner(
Owner_Id int primary key not null auto_increment,
Name varchar(60) not null,
Address varchar (255) not null,
Email varchar(100) not null
);

CREATE TABLE Garage(
Garage_Id int primary key not null auto_increment,
Address varchar(255) not null
);

CREATE TABLE UserType(
Type_Id int primary key not null,
Type_name varchar(20)
);

CREATE TABLE Users(
User_Id int not null auto_increment,
Type_Id int not null,
Name varchar(60) not null,
LoginName varchar(60) not null,
Password varchar(60) not null,
Garage_Id int not null,
FOREIGN KEY(Type_Id) REFERENCES UserType(Type_Id),
FOREIGN KEY(Garage_Id) REFERENCES Garage(Garage_Id),
PRIMARY KEY(User_Id)
);

CREATE TABLE Car(
Car_Reg varchar(10) not null,
Owner_Id int,
Make varchar(20) not null,
Model varchar(30) not null,
MadeDate year(4) DEFAULT '2013',
PRIMARY KEY(Car_Reg),
FOREIGN KEY(Owner_Id) REFERENCES Owner(Owner_Id)
);

CREATE TABLE Booking(
Book_Id int not null auto_increment,
Car_Reg varchar(10) not null,
BDate date not null,
BTime varchar(10) not null,
Garage_Id int,
PRIMARY KEY(Book_Id),
FOREIGN KEY(Car_Reg) REFERENCES Car(Car_Reg),
FOREIGN KEY(Garage_Id) REFERENCES Garage(Garage_Id)
);


CREATE TABLE TestResults(
User_Id int not null,
Car_Reg varchar(10) not null,
Alignment smallint not null,
Suspension smallint not null,
Brakes smallint not null,
Exhaust_Emission smallint not null,
Head_Lights smallint not null,
TestDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
KEY User_Id (User_Id) USING BTREE,
KEY Car_Reg (Car_Reg) USING BTREE,
CONSTRAINT testresults_ibfk_1 FOREIGN KEY (User_Id) REFERENCES Users(User_Id),
CONSTRAINT testresults_ibfk_2 FOREIGN KEY (Car_Reg) REFERENCES Car(Car_Reg)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
--
--
/*POPULATING DATABASE*/
--
--
--
/**/
/*Owner*/
/**/
INSERT INTO Owner (Name,Address,email) VALUES ('Lev S. Hopper','1984 Pellentesque Av.','congue.elit@pharetrasedhendrerit.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Lionel R. Navarro','520-6436 Odio. Ave','Fusce.diam@Nullaeu.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Jade P. Turner','P.O. Box 647, 3830 Lorem Rd.','Donec.tempor.est@tempuslorem.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Melanie M. Ball','P.O. Box 904, 9100 Suspendisse Ave','mollis.Duis.sit@aaliquet.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Cara D. Mooney','P.O. Box 212, 9097 Scelerisque Avenue','Nunc.ut.erat@et.org');
INSERT INTO Owner (Name,Address,email) VALUES ('Evangeline L. Conley','520-3552 Elit. Street','sed.dui@id.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Zephr U. Vargas','P.O. Box 819, 7745 Litora Street','neque.Sed.eget@Suspendisse.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Ryan N. Shepard','3164 Cras Av.','Sed@luctusvulputatenisi.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Sasha K. Hart','9454 Aliquam Street','Nulla.eu.neque@luctusfelis.ca');
INSERT INTO Owner (Name,Address,email) VALUES ('Judith F. Ratliff','938-855 Nunc St.','magnis.dis@massa.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Yolanda V. Rutledge','P.O. Box 169, 3066 Massa Ave','sit.amet@bibendumullamcorperDuis.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Boris X. Higgins','Ap #204-6013 Amet Rd.','ipsum@Cum.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Quyn G. Hall','P.O. Box 765, 6809 Ut, St.','dui.semper@ProinultricesDuis.org');
INSERT INTO Owner (Name,Address,email) VALUES ('Katell A. Koch','Ap #866-7094 Maecenas Rd.','enim.nisl.elementum@Suspendissealiquet.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Kiara V. Mathis','P.O. Box 781, 7807 Ligula. St.','Phasellus.elit@nulla.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Aileen B. Pope','Ap #589-3594 Adipiscing Street','lobortis.quis@venenatisamagna.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Zia H. Witt','Ap #614-9804 Ultrices. Street','dignissim.lacus.Aliquam@sitametdiam.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Lara U. Rogers','P.O. Box 343, 3161 Cum St.','eu.enim@suscipit.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Sage U. Spears','P.O. Box 715, 6608 Facilisis St.','Sed.id.risus@est.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Ivana N. Levine','P.O. Box 688, 8962 Sagittis Street','ultricies.ornare@Suspendissetristiqueneque.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Avye C. Sexton','6761 Auctor, St.','felis.ullamcorper@intempuseu.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Stephanie P. Glover','Ap #456-1796 Nulla St.','Proin.velit@tellus.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Rae P. Barlow','7782 Quis St.','aliquam@Nunc.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Priscilla N. Wilkerson','Ap #995-9440 Tristique Street','cursus.Nunc.mauris@NuncmaurisMorbi.ca');
INSERT INTO Owner (Name,Address,email) VALUES ('Maile N. Alvarez','P.O. Box 884, 5886 Metus. Rd.','a.facilisis.non@Donec.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Justina R. Leon','Ap #497-9115 Sit Street','magna.Cras@gravidanunc.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Quemby J. Coleman','Ap #508-5604 Neque Ave','magna.Ut@odioNaminterdum.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Xena G. Sparks','P.O. Box 817, 1124 Tempor, Avenue','In.condimentum@sociisnatoque.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Orson X. Carey','214-487 Sed Avenue','iaculis@et.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Sonia T. Macdonald','P.O. Box 577, 891 Tellus Street','congue.turpis@anteipsumprimis.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Octavia V. Avila','871-1980 Quis Av.','augue.porttitor.interdum@gravida.ca');
INSERT INTO Owner (Name,Address,email) VALUES ('Neve Y. Best','Ap #380-5769 Morbi Ave','sit@lacusAliquamrutrum.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Melvin G. Foley','P.O. Box 495, 3726 Neque Avenue','ac.mattis.ornare@Praesent.org');
INSERT INTO Owner (Name,Address,email) VALUES ('Jenna P. Patterson','Ap #437-6897 Proin St.','lacus@imperdietnecleo.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Rinah V. Ramos','1935 Magna Av.','eget.volutpat.ornare@Proin.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Austin S. Barnes','365-2218 Sapien. Road','tellus@luctus.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Alea J. Cantrell','P.O. Box 589, 6717 Ac St.','ut.pharetra.sed@mauris.ca');
INSERT INTO Owner (Name,Address,email) VALUES ('Solomon K. Holman','Ap #134-9541 Mus. Road','et.eros@et.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Lynn P. Cantrell','Ap #415-5560 Lorem. Street','auctor.Mauris@disparturient.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Risa U. Patrick','4280 Eu Road','sed.pede@sociosquad.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Charlotte F. Delaney','Ap #113-6271 Duis Rd.','lectus.sit@Aliquam.org');
INSERT INTO Owner (Name,Address,email) VALUES ('Galena E. Cameron','Ap #682-9320 Diam Rd.','Aliquam.vulputate@enim.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Evangeline N. Underwood','P.O. Box 309, 9700 Purus, Av.','amet@ut.ca');
INSERT INTO Owner (Name,Address,email) VALUES ('Kelsey K. Murray','6790 Vivamus St.','et.magnis.dis@auguescelerisquemollis.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Carly T. Hammond','P.O. Box 162, 1773 Morbi Ave','Nam@turpis.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Rogan N. Lloyd','P.O. Box 164, 2459 Nec St.','at.libero@convallisestvitae.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Quynn M. Morton','9059 Ipsum Street','a@pedenecante.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Conan K. Cannon','P.O. Box 178, 7536 Maecenas Rd.','lobortis.nisi.nibh@bibendum.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Meredith Y. Hansen','699-5470 Pede Road','sem@sempertellus.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Dominique A. Reynolds','839-8588 Mauris. Av.','Mauris.molestie.pharetra@nec.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Sasha B. Booker','P.O. Box 981, 655 Ut Ave','vel.nisl.Quisque@blanditatnisi.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Giselle S. Patton','702-4899 Egestas Rd.','ac@cursus.ca');
INSERT INTO Owner (Name,Address,email) VALUES ('Wyoming D. Berger','P.O. Box 766, 1563 Non Road','risus.odio@tincidunttempusrisus.org');
INSERT INTO Owner (Name,Address,email) VALUES ('Shaeleigh W. Knight','Ap #546-3181 Lorem Av.','ac@posuereenimnisl.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Hammett N. Sawyer','988-4621 Ac Rd.','Quisque@dui.ca');
INSERT INTO Owner (Name,Address,email) VALUES ('Venus S. Chapman','P.O. Box 264, 7860 Cursus. Avenue','tellus.lorem@amet.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Destiny F. Rios','8936 Metus. Street','egestas.nunc@placerat.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Brenda T. Cole','Ap #643-398 Mattis Road','placerat@ascelerisque.org');
INSERT INTO Owner (Name,Address,email) VALUES ('Lawrence F. Sparks','795-1821 Risus. Av.','mauris.Integer@vulputaterisus.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Ora X. Lynn','Ap #709-8999 Nunc St.','eget@pellentesqueSed.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Zachary J. Wong','P.O. Box 293, 5446 Risus. St.','tempor.augue.ac@Integersem.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Keane S. Villarreal','1299 Non, Ave','tempus.mauris.erat@dui.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Isaiah V. Walter','Ap #290-9937 Semper St.','enim.diam.vel@sit.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Dane E. Cotton','7942 Lacinia Avenue','sollicitudin@ligulaAenean.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Demetria R. Fitzpatrick','5687 Quis Avenue','risus.at@volutpatNulla.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Haviva N. Townsend','3604 Sollicitudin Avenue','massa.non@neque.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Melodie H. Finley','Ap #290-1873 Sed Rd.','interdum.enim@Inornaresagittis.org');
INSERT INTO Owner (Name,Address,email) VALUES ('Xaviera P. Hodges','Ap #625-6158 Semper Rd.','aliquam.enim.nec@cursusinhendrerit.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Hasad V. Wood','1726 Tempus Ave','ac@nibhPhasellus.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Echo O. Kidd','Ap #401-2586 Quis St.','sed.hendrerit.a@metusInnec.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Kirsten F. Stephens','Ap #489-3473 Risus, Rd.','sem@Inscelerisque.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Hu C. Buck','2237 Non, Ave','aliquam.iaculis.lacus@interdumligula.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Boris V. Raymond','Ap #661-7984 Nisi Rd.','ornare.elit.elit@tempuseu.ca');
INSERT INTO Owner (Name,Address,email) VALUES ('Isaiah Q. Golden','Ap #672-4379 Sagittis. Rd.','cursus.Nunc.mauris@Nuncmauris.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Isabelle I. Summers','549-2283 Eget, St.','Vivamus.non.lorem@Donecluctusaliquet.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Kyle U. Nieves','663-8826 Aliquet Avenue','quis.pede.Suspendisse@Proindolor.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Odessa R. Holmes','418-9810 Orci, Rd.','sit@convallisestvitae.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Angela T. Grant','Ap #617-1294 Suspendisse Rd.','Phasellus.fermentum.convallis@necanteblandit.ca');
INSERT INTO Owner (Name,Address,email) VALUES ('Morgan C. Dunn','891 Sodales Avenue','erat.Sed@diam.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Brent E. Marks','P.O. Box 379, 5743 Facilisis St.','auctor.quis@felis.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Felix D. Rice','6726 Ante. Street','Lorem.ipsum.dolor@conubianostra.org');
INSERT INTO Owner (Name,Address,email) VALUES ('Adele H. Riley','7286 Odio Avenue','molestie.arcu@vitaesemperegestas.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Yoko O. Harrell','634-5987 Eget Street','libero.Proin.mi@convallis.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Driscoll N. Kidd','5656 Purus Street','ipsum.nunc@nibhvulputatemauris.ca');
INSERT INTO Owner (Name,Address,email) VALUES ('Xyla S. Benton','534-8570 Varius. Rd.','eros@luctusvulputate.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Lael T. Mccormick','Ap #335-2766 Vulputate Road','felis@pharetraQuisque.org');
INSERT INTO Owner (Name,Address,email) VALUES ('Jesse W. Pugh','P.O. Box 961, 273 Auctor Avenue','Lorem@nunc.ca');
INSERT INTO Owner (Name,Address,email) VALUES ('Nicole X. England','Ap #958-7598 Dolor, Rd.','est.ac@dolorQuisque.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Kaseem M. Dale','7027 Libero. St.','urna.et.arcu@Morbi.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Gretchen N. Hahn','Ap #228-8169 Nunc Street','magna.et.ipsum@Quisque.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Brendan O. Owen','Ap #968-9940 Ipsum Rd.','odio@sitametdiam.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Yvonne N. Daniels','P.O. Box 891, 4217 Quisque Street','non.leo@facilisismagna.org');
INSERT INTO Owner (Name,Address,email) VALUES ('Levi G. Vega','149-4178 Sagittis St.','dui@aliquam.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Thaddeus J. Little','151-5619 Nullam St.','Morbi.metus.Vivamus@tristique.com');
INSERT INTO Owner (Name,Address,email) VALUES ('Yuri Z. Scott','5890 Ut Av.','mauris@elit.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Alexa C. Golden','Ap #659-3854 Diam Road','litora.torquent@est.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Petra B. Perry','4316 Sed Rd.','nunc.risus@ornarefacilisiseget.co.uk');
INSERT INTO Owner (Name,Address,email) VALUES ('Carla E. Gallagher','Ap #828-1790 Ac St.','Phasellus.dapibus.quam@disparturient.edu');
INSERT INTO Owner (Name,Address,email) VALUES ('Hyacinth B. Bright','P.O. Box 843, 1055 Ultricies Av.','lacinia.mattis.Integer@aliquam.net');
INSERT INTO Owner (Name,Address,email) VALUES ('Stacey H. Brady','952-6412 Vitae, Ave','non@vestibulum.net');
/**/
/**/
/**/
/*Garage*/
/**/
/**/
/**/
INSERT INTO Garage (Address) VALUES ('7972 At, Rd.');
INSERT INTO Garage (Address) VALUES ('Ap #332-4934 Nec St.');
INSERT INTO Garage (Address) VALUES ('535-2550 Quisque St.');
INSERT INTO Garage (Address) VALUES ('Ap #436-2307 Consectetuer Street');
INSERT INTO Garage (Address) VALUES ('Ap #497-5951 Ipsum St.');
INSERT INTO Garage (Address) VALUES ('P.O. Box 435, 7456 Sed, Rd.');
INSERT INTO Garage (Address) VALUES ('947-5802 Magna Av.');
INSERT INTO Garage (Address) VALUES ('956-2075 Commodo St.');
INSERT INTO Garage (Address) VALUES ('Ap #274-2407 Dignissim Rd.');
INSERT INTO Garage (Address) VALUES ('420-6215 Auctor Avenue');
INSERT INTO Garage (Address) VALUES ('1766 Urna. St.');
INSERT INTO Garage (Address) VALUES ('418-9802 Molestie Rd.');
INSERT INTO Garage (Address) VALUES ('P.O. Box 289, 2321 Auctor St.');
INSERT INTO Garage (Address) VALUES ('298-5273 Aliquam, Rd.');
INSERT INTO Garage (Address) VALUES ('972-3156 Leo. Street');
INSERT INTO Garage (Address) VALUES ('297-2374 Eleifend, Avenue');
INSERT INTO Garage (Address) VALUES ('Ap #420-1826 Imperdiet St.');
INSERT INTO Garage (Address) VALUES ('1549 Eu Road');
INSERT INTO Garage (Address) VALUES ('8803 Ut St.');
INSERT INTO Garage (Address) VALUES ('Ap #763-1333 Interdum Rd.');
INSERT INTO Garage (Address) VALUES ('Ap #979-9274 Nunc St.');
INSERT INTO Garage (Address) VALUES ('147-8848 In Avenue');
INSERT INTO Garage (Address) VALUES ('1227 Pede Rd.');
INSERT INTO Garage (Address) VALUES ('Ap #607-7131 Etiam Ave');
INSERT INTO Garage (Address) VALUES ('Ap #221-1465 Fringilla Avenue');
INSERT INTO Garage (Address) VALUES ('866-2937 Curabitur Avenue');
INSERT INTO Garage (Address) VALUES ('P.O. Box 203, 4962 Sem St.');
INSERT INTO Garage (Address) VALUES ('Ap #293-5241 Augue. St.');
INSERT INTO Garage (Address) VALUES ('111-8286 Enim. St.');
INSERT INTO Garage (Address) VALUES ('P.O. Box 860, 3558 Consectetuer St.');
/**/
/**/
/**/
/*UserType*/
/**/
/**/
/**/
INSERT INTO UserType (Type_Id,Type_Name) VALUES ('1','Administrator');
INSERT INTO UserType (Type_Id,Type_Name) VALUES ('2','Mechanic');
/**/
/**/
/**/
/*User*/
/**/
/**/
/**/
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (1,'April Case','vitae.orci@egestasa.co.uk','BE31573418345230',19);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (1,'Vivian Norman','eros.turpis.non@mollisvitae.org','GB90RZPT78929313256024',7);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (1,'Rina Sosa','tellus@leo.net','CH5132461902092024926',7);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (1,'Lawrence Cleveland','Nunc@Maurisblanditenim.co.uk','FO5057293017392916',16);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (1,'Ella Nolan','nunc.sed.libero@feugiattellus.com','PL68499709169350593910120985',16);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (1,'Slade Madden','lacinia@enimSed.net','GR4440773990843306203038937',10);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (1,'Jonah Cash','Sed.dictum.Proin@natoque.org','SA1883890961198807770188',19);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (1,'Ursula Patel','ultricies@elementum.net','MU2190275694303691432029905850',6);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (1,'Nayda Bradshaw','diam@mauris.ca','TR431916136472939701323118',30);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (1,'Vanna West','euismod.mauris.eu@orciconsectetuereuismod.com','PL98938827794221688692339456',12);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Celeste Joseph','tempus.mauris.erat@Duisvolutpat.ca','SA2363532520944149419290',22);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Myles Melendez','vitae.aliquet@neque.org','MU2340071935994288237984478867',14);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Arden Kirby','ornare.facilisis@Donectempuslorem.co.uk','KZ103609663081817147',5);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Harlan Coffey','enim.Etiam@parturient.edu','BA751377714113736794',16);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Ishmael Rhodes','Aliquam@mollislectuspede.edu','MR3662801389266686943491953',4);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Constance Terry','adipiscing.Mauris.molestie@enimnonnisi.edu','HR8309846260600212911',13);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Griffin Finch','nibh@fermentum.ca','MU4846120462070188092463768024',30);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Whitney Ortega','lorem.eget@volutpat.net','GT35006272862457609312782143',13);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Genevieve Palmer','augue.eu.tempor@penatibus.co.uk','GB61HRQR64388075664956',15);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Samuel Deleon','dictum.Phasellus@nisimagnased.org','MR2467750581570524136757736',27);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Melanie Avila','mauris.sit.amet@necurna.net','TR590709926226734894592286',24);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Nissim Jordan','ut.pellentesque.eget@sagittis.org','CZ2906140312297015750074',4);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Price Whitaker','non@sapien.co.uk','MK64929897743911736',13);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Melodie Morgan','sem@pellentesquetellussem.edu','NL69SZCM1178274419',8);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Orson Spears','blandit.Nam@Phasellus.ca','LI9028060754262794843',18);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Ross Foley','vulputate.mauris.sagittis@risus.net','KZ750234141991365350',20);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Kiayada Suarez','lorem.ac@semconsequatnec.co.uk','GE51424040961896697104',3);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Imogene Brady','facilisis@Morbiquis.com','MK09443788951509189',13);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Lenore Davenport','vulputate.eu@lacusNullatincidunt.co.uk','BG75XAMO75949913939675',19);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Caesar Wooten','Donec.luctus@metus.edu','KW2871776180231937755517890947',8);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Walter Decker','ipsum.ac@amet.co.uk','HR9780354409847843032',25);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Linda Hester','tellus@hendreritconsectetuercursus.net','SK2058741057075522580994',22);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Summer Bates','neque.venenatis.lacus@Nuncmauriselit.ca','PT74258715871045185485378',9);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Teagan Mullins','cursus@tempor.net','SK6216139483229431695724',11);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Gwendolyn Sawyer','semper.auctor@magnatellus.net','GE75013460140340352488',7);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'George Barry','suscipit@eleifend.org','CZ6323647724417606873162',22);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Shafira Hogan','at.iaculis@anteblanditviverra.net','NO2096132955577',17);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Ila Acosta','mi.tempor@urnaNullamlobortis.edu','LB79483723930394463339609628',2);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Ayanna Ball','Etiam@nonjusto.org','VG5485067832048608645194',27);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Latifah Reynolds','gravida.Praesent@diamatpretium.edu','SI76836277536412452',14);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Kelsey Thornton','mauris.sapien@fringilla.com','AL43174453293381410084050661',22);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Brady Ratliff','molestie@velmauris.co.uk','LU419872816555544038',24);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Sonya Fitzpatrick','et.ultrices@volutpatNulla.edu','MT94ULLA43905752557296752107471',13);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Delilah Livingston','consectetuer@vulputaterisus.co.uk','LI6992868824914134243',30);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Kitra Clark','ante.dictum.mi@eratnonummy.net','CR5808368677150751158',22);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Candace Sloan','Ut.semper.pretium@etarcuimperdiet.net','KZ593689822947141240',1);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Casey Guy','est@purussapiengravida.ca','SE4610292317561338316737',8);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Lynn Monroe','egestas.rhoncus@mauris.edu','PL23853557217799829576331788',1);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Lucas Galloway','Suspendisse@conubia.net','RO86HNKU0885966280893839',28);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Lois Howe','magna.Phasellus@placerateget.net','SM1045841899974220009388803',24);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Moses Lewis','Curabitur@utipsum.org','CR3618548997228332873',7);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Kimberly Sweet','risus.In.mi@velit.com','IL900312636840130771881',16);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Colby Daugherty','risus.Morbi@blanditviverra.ca','BA710486578338301076',11);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Basil Conrad','feugiat@risusNulla.ca','CR4670294917314391078',22);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Owen Payne','Sed@nascetur.edu','DE02059841126080504012',14);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Audrey Wells','purus.accumsan.interdum@Quisqueac.net','GI36QRIH702496006688122',20);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Daquan Barron','ipsum.nunc@augueporttitorinterdum.net','RS45180770269150307946',9);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Clark Preston','ultrices.iaculis@consectetuermaurisid.co.uk','MR7392368589591563749970886',6);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Fallon Wagner','cursus.vestibulum.Mauris@natoquepenatibuset.org','SM4651023119640390517207484',8);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Cadman Mcpherson','sollicitudin.adipiscing@mollis.org','GL5731884076225836',10);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Lance Wiggins','sodales@Fuscefermentumfermentum.ca','HU67730423656974505529621778',22);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Vielka Mcfadden','tincidunt.dui@nonmassanon.org','PS714594429955126246192441737',25);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Sloane Henry','adipiscing@ultriciesornare.net','MR8637670425430903043151025',1);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Shea Hines','lobortis@dolorFusce.co.uk','MD5576945276110634255456',9);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Lacey Kane','ornare@ametrisus.org','SA4720818178930095806567',22);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Lewis Weaver','diam.Sed@a.ca','SM4447256374437922157564712',10);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Allistair Wood','eu@nequevitaesemper.edu','MC5084388487156691652745861',22);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Unity Vazquez','lorem@vestibulumneque.co.uk','AE858501754688811458383',21);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Sandra Chambers','ac@quispedeSuspendisse.com','GR5426819203437397966764924',14);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Arthur Mcknight','lacinia@tristiquepharetraQuisque.net','GE76936543377132351873',14);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Zorita Vaughan','posuere.at.velit@enim.edu','GR3918543599842046413753644',23);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Cheyenne Wilson','sem@ultrices.com','PT57067863424663362694536',9);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Meghan Warner','iaculis.aliquet@etmagnaPraesent.net','KZ304257491121555385',28);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Kamal Good','vulputate.posuere@aodio.edu','GT34061065652080501146252330',3);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Ann Glover','Vivamus.nibh@inmagna.net','SI23104097114214253',23);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Talon Berry','amet.consectetuer.adipiscing@neceleifend.org','FR1967052335196668317100086',24);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Hyatt Hughes','viverra.Donec@ultricesposuerecubilia.net','PL04754516545614624606129533',6);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Chancellor Hayden','orci.lobortis.augue@Nunc.edu','FR0742223495068104852278178',20);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Vivian Salas','tempus.risus.Donec@disparturientmontes.co.uk','RO48DOBV9621646821933554',3);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Harper Alvarez','aliquet.Proin.velit@tempus.co.uk','GI12OBCS722848503722802',25);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Luke Mccray','Duis.dignissim@nonnisiAenean.com','PT16745864406496261245231',2);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Wanda Campbell','at.arcu@miacmattis.co.uk','KZ972967570056874313',27);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Leah Tate','eget@odiosempercursus.com','GI20ERBD132580325278050',23);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Tanisha Lindsay','eu.tellus.eu@lacusvestibulumlorem.net','SE6752389602838160572246',10);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Jenette Moon','Maecenas.iaculis@mollis.org','SM8117132529551934835554050',4);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Katelyn Lawson','metus@lorem.com','MC3317070253720387453818411',3);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Kelsie Suarez','dui.semper@lobortis.ca','LT102422501318712129',16);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Ulla Frank','at@enimnectempus.ca','PK3043679518144390108857',6);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Garth Suarez','mus.Proin@lorem.co.uk','GE94264488021019972770',4);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Amaya Kent','senectus.et.netus@nonarcuVivamus.org','GE33547791231882410948',3);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Leilani Barker','parturient@quisdiam.org','CR9693837992906067688',19);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Alika Taylor','risus@semutdolor.ca','LB58419745533339770654340026',26);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Keely Mack','augue.scelerisque@Nunccommodoauctor.edu','TR115916887816715246659444',5);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Edward Waters','vel.faucibus.id@ligula.edu','TN9097414373905515875071',29);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Zia Beck','viverra.Maecenas@sit.org','FO5740676631604018',21);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Shaeleigh Hodge','iaculis.enim.sit@egestasFusce.net','RO41JZRH6201358208803012',24);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Linus Holmes','sem.eget@feugiatnecdiam.com','VG7657878901644817417314',22);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Rahim Moreno','adipiscing.lacus.Ut@magna.edu','PL95584647461678732373155750',30);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Sonya Branch','nisl.Maecenas@Duisvolutpat.co.uk','AZ13095779106095207925960369',26);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Petra Ayers','scelerisque@laoreetipsum.co.uk','GI10AMHL044909611905175',1);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (1,'Admin','admin','admin',1);
INSERT INTO Users (Type_Id,Name,LoginName,Password,Garage_Id) VALUES (2,'Mechanic','mechanic','mechanic',1);
/**/
/**/
/**/
/*Car*/
/**/
/**/
/**/
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('9LTREUSF', 81, 'Abarth','Audi TT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('L2545UEN', 71, 'BMW','Aston Martin Rapide S');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('47XZUKQQ', 32, 'Ford','Ford Edge');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('ACLUE6BZ', 48, 'Toyota','Toyota Prius V');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('WGCNYWUQ', 17, 'Ascari','Ascari KZ1');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('45CP4YRT', 17, 'Abarth','Audi A4');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('PTDUR2YW', 67, 'Bentley','Acura ZDX');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('8AJGXM88', 95, 'Alfa Romeo','Ascari FGT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('LH7GPXQ9', 74, 'Acura','Audi A4');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('UKP95PZ2', 6,  'Volvo','Bentley Flying Spur');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('R9MN96ES', 45, 'Ascari','Bentley Bentayga');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('J45GEEZV', 66, 'Ford','Focus');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('55TAJKHL', 14, 'Ferrari','Bentley Bentayga');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('WV328NCL', 42, 'Acura','BMW X6 M');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('BVVEE5JY', 16, 'Volvo','Alfa Romeo Arna 3-door');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('G4FLELY6', 98, 'Audi','Bentley Flying Spur');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('SW6JE9G3', 27, 'Alfa Romeo','Ascari KZ1');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('WHFGQMFG', 55, 'Bentley','Ferrari FF');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('KBXKHKE9', 39, 'Audi','Audi A4');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('TA7L2NJX', 50, 'Aston Martin','Bentley Bentayga');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('857C9VYD', 16, 'Bentley','Acura TL');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('G2XKUYA6', 52, 'Volvo','Abarth Punto Evo');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('WGEMFPZK', 82, 'Volvo','Aston Martin DB9');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('6S62ZJDJ', 61, 'Abarth','Ascari KZ1');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('DFPKBTFE', 47, 'Ford','Ascari FGT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('PJE84VB3', 98, 'Ferrari','Alfa Romeo Spider');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('AA2VVKVV', 77, 'Toyota','Ascari KZ1');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('DFQFTEWG', 12, 'Ascari','Audi TT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('TZ2CB6BH', 62, 'Audi','Ascari KZ1');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('6GYAUEB8', 15, 'Bentley','CONTINENTAL GT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('WGF5DTEX', 55, 'Aston Martin','Audi A4');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('55DG7TCY', 49, 'Ford','Ford Edge');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('UQHNH49U', 32, 'Ferrari','Ford Edge');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('PJUPDFGH', 28, 'Toyota','Toyota Prius c');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('XCCG948N', 89, 'Ford','Bentley Flying Spur');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('TBQTLEUM', 48, 'Aston Martin','Bentley Flying Spur');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('P99X4H7B', 19, 'BMW','Ascari FGT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('526AEWNK', 47, 'Abarth','Ford Edge');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('FD8M9KCW', 62, 'Volvo','Alfa Romeo Arna 3-door');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('PGKFR87V', 83, 'Audi','Ford Edge');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('M2B2R5D2', 61, 'Ascari','Alfa Romeo Arna 3-door');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('WB464D8K', 37, 'Ferrari','Ascari KZ1');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('2BK4YWKM', 39, 'Audi','Ascari FGT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('XKZDM6TP', 73, 'BMW','Aston Martin DB9');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('GPSRSK5S', 52, 'Volvo','Audi A4');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('C3FGUZ9A', 44, 'Toyota','Acura TL');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('VSMBRF69', 19, 'BMW','Toyota Prius V');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('23FGN8EW', 54, 'BMW','Alfa Romeo Arna 3-door');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('A9FGGFNS', 71, 'Bentley','Bentley Flying Spur');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('JQM6M49F', 34, 'Ferrari','Aston Martin DB9');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('H5TMKEQZ', 44, 'Acura','Alfa Romeo Arna 3-door');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('S7MWNE5Z', 78, 'Ferrari','Toyota Prius V');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('D5SBHTUB', 72, 'Volvo','Audi TT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('JP7N9DAX', 68, 'Volvo','Ascari FGT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('9G5C2PWV', 75, 'Abarth','Aston Martin DB9');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('FXW9CTFC', 92, 'Ford','Abarth Punto Evo');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('8VE3JXUH', 43, 'Alfa Romeo','Toyota Prius c');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('2SU5NVEZ', 4,  'BMW','Alfa Romeo Spider');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('PMQYXRD3', 93, 'Volvo','Abarth 500');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('PK36HUNN', 90, 'Aston Martin','Toyota Prius c');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('6DRCJLV4', 78, 'Alfa Romeo','Abarth 500');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('FQ5DM44B', 6,  'Ascari','Ascari FGT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('MBLCF83Y', 10, 'Acura','BMW X6 M');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('3FBQWHD9', 49, 'Acura','Toyota Prius c');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('QY3TZ699', 36, 'Abarth','Alfa Romeo Arna 3-door');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('8BYZQ89R', 8,  'BMW','Ford Edge');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('AM3LH23L', 65, 'Ford','Abarth Punto Evo');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('AY8RLDQ3', 31, 'Aston Martin','Ascari KZ1');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('LPNDSKZH', 91, 'Acura','Acura MDX');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('S24QSBAS', 94, 'Ascari','Ascari FGT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('ZVFTZ5KC', 45, 'Aston Martin','Ascari KZ1');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('YX44CQYZ', 36, 'BMW','BMW X6 M');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('RND9QRB2', 97, 'Ferrari','Acura ZDX');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('VYVP8D4G', 72, 'Toyota','Aston Martin Rapide S');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('NHM5PVAG', 15, 'Toyota','Abarth 500');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('TXXRSTUX', 72, 'Ascari','Ferrari FF');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('TB8CANT2', 52, 'Alfa Romeo','Alfa Romeo Spider');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('95FWHW5H', 98, 'Ford','Ferrari FF');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('ND6Z3F7E', 68, 'BMW','Alfa Romeo Arna 3-door');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('27T2SERM', 52, 'Alfa Romeo','BMW X6 M');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('SP6HF9Q5', 92, 'Volvo','Ferrari FF');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('HDRLV3WL', 95, 'Toyota','Aston Martin DB9');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('8UHYGG79', 64, 'Acura','Acura TL');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('J93FTBAS', 91, 'Toyota','Aston Martin DB9');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('K4LBURCW', 83, 'Bentley','Bentley Bentayga');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('CAEHMFZL', 82, 'Ford','Ascari KZ1');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('9TFR4AHS', 65, 'Volvo','Audi TT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('Y948MB6M', 72, 'Acura','Toyota Prius c');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('4ZDC93QL', 33, 'BMW','Audi TT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('Q4VY9NW2', 7,  'Acura','Acura TL');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('QCZMU8XN', 19, 'Aston Martin','Audi TT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('DTV2YSRY', 92, 'Ascari','Abarth 500');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('37D9RZ6S', 8,  'Abarth','Acura TL');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('DKATMK97', 93, 'Ferrari','Alfa Romeo Arna 3-door');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('Y6RJZQRY', 74, 'Acura','Ascari FGT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('FTJ37HS3', 80, 'Ford','Audi TT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('WP3SAWDX', 83, 'Alfa Romeo','Ascari FGT');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('3RNR5UAV', 18, 'Audi','Ferrari FF');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('68HMJY3X', 4,  'Ascari','Ferrari FF');
INSERT INTO Car (Car_Reg,Owner_Id,Make,Model) VALUES ('ZBG6KBZY', 91, 'Ferrari','Bentley Flying Spur');
/**/
/**/
/**/
/*Booking*/
/**/
/**/
/**/
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-11','08:11:34','68HMJY3X', 1);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-26','11:21:34','QY3TZ699', 1);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-16','15:11:34','Q4VY9NW2', 1);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-22','10:21:45','HDRLV3WL', 1);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-07-04','09:21:45','FTJ37HS3', 1);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-15','11:31:55','ZBG6KBZY', 1);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-24','12:30:22','Q4VY9NW2', 1);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-24','14:52:33','9LTREUSF', 14);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-10','07:25:43','L2545UEN', 21);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-20','05:15:13','47XZUKQQ', 18);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-09','13:57:13','ACLUE6BZ', 13);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-04','10:06:25','WGCNYWUQ', 18);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-13','00:41:58','45CP4YRT', 20);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-23','14:08:03','PTDUR2YW', 26);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-02','12:10:29','8AJGXM88', 24);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-24','10:25:17','LH7GPXQ9', 17);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-16','13:15:36','UKP95PZ2', 4);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-22','02:23:28','R9MN96ES', 11);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-29','05:40:25','J45GEEZV', 29);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-07','17:53:56','55TAJKHL', 9);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-29','20:22:01','WV328NCL', 2);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-07','08:55:09','BVVEE5JY', 20);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-01','12:16:59','G4FLELY6', 13);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-17','07:38:34','SW6JE9G3', 23);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-26','16:34:46','WHFGQMFG', 13);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-01','17:51:21','KBXKHKE9', 10);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-04','15:08:36','TA7L2NJX', 27);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-22','05:34:30','857C9VYD', 2);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-24','06:07:16','G2XKUYA6', 14);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-04','15:56:46','WGEMFPZK', 24);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-08','07:19:44','6S62ZJDJ', 20);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-27','05:35:55','DFPKBTFE', 28);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-03','18:50:34','PJE84VB3', 20);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-15','20:04:25','AA2VVKVV', 9);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-07','13:28:17','DFQFTEWG', 25);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-07','11:03:49','TZ2CB6BH', 18);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-11','19:46:07','6GYAUEB8', 25);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-02','10:16:43','WGF5DTEX', 23);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-23','02:41:09','55DG7TCY', 7);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-25','15:00:15','UQHNH49U', 17);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-23','15:35:17','PJUPDFGH', 10);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-22','07:29:08','XCCG948N', 28);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-22','04:06:34','TBQTLEUM', 2);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-12','16:40:55','P99X4H7B', 15);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-04','19:29:03','526AEWNK', 21);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-13','07:21:48','FD8M9KCW', 4);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-21','05:51:21','PGKFR87V', 20);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-23','13:53:37','M2B2R5D2', 14);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-26','12:06:47','WB464D8K', 9);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-02','17:31:50','2BK4YWKM', 25);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-02','15:19:57','XKZDM6TP', 22);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-22','05:19:13','GPSRSK5S', 4);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-28','06:00:37','C3FGUZ9A', 5);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-11','07:28:56','VSMBRF69', 1);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-04','02:42:59','23FGN8EW', 28);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-14','19:10:17','A9FGGFNS', 5);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-28','09:12:39','JQM6M49F', 9);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-05','02:29:38','H5TMKEQZ', 17);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-23','14:49:25','S7MWNE5Z', 6);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-03','21:59:06','D5SBHTUB', 20);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-01','20:39:46','JP7N9DAX', 13);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-18','05:07:29','9G5C2PWV', 19);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-31','16:54:19','FXW9CTFC', 14);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-09','23:28:28','8VE3JXUH', 3);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-02','10:36:22','2SU5NVEZ', 12);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-18','17:22:26','PMQYXRD3', 16);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-25','13:03:16','PK36HUNN', 26);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-13','15:40:43','6DRCJLV4', 2);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-02','21:55:18','FQ5DM44B', 3);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-08','20:31:32','MBLCF83Y', 28);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-08','14:58:49','3FBQWHD9', 24);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-01','06:39:59','QY3TZ699', 2);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-03','17:39:17','8BYZQ89R', 23);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-18','13:00:49','AM3LH23L', 8);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-21','12:06:11','AY8RLDQ3', 9);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-10','04:13:35','LPNDSKZH', 13);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-18','02:28:01','S24QSBAS', 21);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-24','14:28:35','ZVFTZ5KC', 6);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-17','00:45:32','YX44CQYZ', 15);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-03','00:59:39','RND9QRB2', 19);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-19','20:31:33','VYVP8D4G', 15);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-20','15:49:32','NHM5PVAG', 12);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-23','13:21:44','TXXRSTUX', 20);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-22','18:04:03','TB8CANT2', 24);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-04','21:37:38','95FWHW5H', 12);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-02','09:41:13','ND6Z3F7E', 13);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-11','03:19:45','27T2SERM', 18);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-06','10:51:25','SP6HF9Q5', 25);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-19','02:21:33','HDRLV3WL', 8);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-21','20:21:18','8UHYGG79', 12);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-20','13:40:20','J93FTBAS', 1);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-10','17:27:38','K4LBURCW', 25);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-30','19:28:02','CAEHMFZL', 26);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-26','22:39:59','9TFR4AHS', 14);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-19','02:39:10','Y948MB6M', 6);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-05','11:24:04','4ZDC93QL', 19);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-04','23:30:28','Q4VY9NW2', 5);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-08','09:05:54','QCZMU8XN', 6);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-30','23:08:34','DTV2YSRY', 11);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-08','20:06:40','37D9RZ6S', 27);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-27','12:20:38','DKATMK97', 7);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-05','20:10:11','Y6RJZQRY', 13);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-04-01','18:11:08','FTJ37HS3', 22);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-21','18:42:42','WP3SAWDX', 1);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-06-09','21:28:49','3RNR5UAV', 16);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-19','07:45:59','68HMJY3X', 26);
INSERT INTO Booking (BDate,BTime,Car_Reg,Garage_Id) VALUES ('2015-05-03','05:40:13','ZBG6KBZY', 20);
/**/
/**/
/**/
/*TestResults*/
/**/
/**/
/**/
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (80,'9LTREUSF',0,0,1,1,1,'2009-05-21 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (93,'L2545UEN',0,0,0,1,1,'2014-01-11 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (81,'47XZUKQQ',1,0,0,0,0,'2013-05-25 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (38,'ACLUE6BZ',1,1,1,1,1,'2011-10-21 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (67,'WGCNYWUQ',0,0,1,0,1,'2012-05-12 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (29,'45CP4YRT',0,1,1,0,1,'2009-08-21 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (11,'PTDUR2YW',0,0,0,0,1,'2002-01-01 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (21,'8AJGXM88',0,0,1,0,0,'2014-02-10 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (87,'LH7GPXQ9',0,0,0,1,1,'2014-01-11 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (7, 'UKP95PZ2',1,1,0,0,0,'2014-04-01 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (94,'R9MN96ES',1,1,1,0,1,'2014-06-23 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (45,'J45GEEZV',0,1,0,0,0,'2012-01-16 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (5, '55TAJKHL',0,1,1,0,1,'2011-03-11 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (50,'WV328NCL',1,1,0,1,1,'2012-05-23 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (94,'BVVEE5JY',1,1,1,0,0,'2014-05-01 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (56,'G4FLELY6',1,0,1,0,1,'2013-03-21 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (88,'SW6JE9G3',1,1,0,1,0,'2013-05-01 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (89,'WHFGQMFG',0,0,0,0,1,'2012-05-01 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (11,'KBXKHKE9',1,1,0,1,0,'2014-05-02 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (23,'TA7L2NJX',0,1,0,1,1,'2012-05-23 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (53,'857C9VYD',0,1,1,0,0,'2013-05-11 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (52,'G2XKUYA6',1,1,0,1,0,'2014-05-13 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (67,'WGEMFPZK',1,0,1,1,1,'2014-05-14 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (11,'6S62ZJDJ',1,1,1,0,1,'2014-05-08 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (45,'DFPKBTFE',1,0,1,1,0,'2014-05-09 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (45,'PJE84VB3',1,1,0,0,0,'2014-05-10 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (62,'AA2VVKVV',1,0,1,0,1,'2014-05-11 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (97,'DFQFTEWG',1,1,1,1,1,'2014-05-13 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (55,'TZ2CB6BH',0,1,1,0,1,'2014-05-15 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (77,'6GYAUEB8',1,1,1,1,0,'2014-05-21 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (22,'WGF5DTEX',1,1,0,0,0,'2014-02-11 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (1, '55DG7TCY',0,1,0,0,1,'2014-01-30 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (30,'UQHNH49U',0,0,0,1,0,'2014-05-11 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (60,'PJUPDFGH',0,1,0,0,0,'2014-06-21 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (96,'XCCG948N',1,0,0,1,1,'2014-03-31 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (51,'TBQTLEUM',1,0,1,1,1,'2014-04-24 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (1, 'P99X4H7B',1,0,0,0,0,'2014-06-22 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (31,'526AEWNK',0,1,1,0,1,'2014-07-25 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (58,'FD8M9KCW',1,0,0,1,1,'2014-09-27 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (89,'PGKFR87V',0,0,1,1,0,'2014-07-29 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (54,'M2B2R5D2',0,0,0,1,1,'2014-04-23 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (32,'WB464D8K',1,0,0,0,1,'2014-05-01 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (92,'2BK4YWKM',1,0,1,1,0,'2014-05-03 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (26,'XKZDM6TP',0,0,0,1,0,'2014-07-10 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (74,'GPSRSK5S',1,1,1,0,0,'2014-08-11 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (59,'C3FGUZ9A',0,0,1,1,0,'2014-06-21 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (50,'VSMBRF69',0,1,0,0,0,'2014-08-25 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (52,'23FGN8EW',1,0,1,0,0,'2014-03-26 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (30,'A9FGGFNS',0,0,1,1,1,'2014-06-27 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (28,'JQM6M49F',0,0,1,1,1,'2014-05-29 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (60,'H5TMKEQZ',0,1,1,1,0,'2014-08-21 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (71,'S7MWNE5Z',0,0,0,1,1,'2014-03-23 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (25,'D5SBHTUB',1,1,0,0,0,'2014-03-20 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (83,'JP7N9DAX',0,0,0,1,0,'2014-04-20 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (71,'9G5C2PWV',1,1,1,1,1,'2014-05-27 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (45,'FXW9CTFC',1,0,1,0,1,'2014-07-11 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (88,'8VE3JXUH',0,1,0,1,1,'2014-01-01 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (43,'2SU5NVEZ',1,1,1,1,0,'2014-02-11 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (23,'PMQYXRD3',1,1,0,1,0,'2014-03-02 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (16,'PK36HUNN',1,0,0,0,0,'2014-01-02 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (89,'6DRCJLV4',0,1,1,1,0,'2014-09-21 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (78,'FQ5DM44B',1,0,0,1,0,'2014-05-23 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (82,'MBLCF83Y',1,0,1,0,1,'2014-08-21 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (11,'3FBQWHD9',1,1,1,0,1,'2014-07-28 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (93,'QY3TZ699',1,0,0,1,0,'2014-02-25 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (78,'8BYZQ89R',0,1,1,0,0,'2014-02-23 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (36,'AM3LH23L',1,0,1,0,1,'2014-03-23 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (8, 'AY8RLDQ3',0,0,0,0,1,'2014-05-22 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (13,'LPNDSKZH',0,1,1,0,0,'2014-04-28 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (72,'S24QSBAS',1,1,1,1,0,'2014-08-25 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (68,'ZVFTZ5KC',0,1,1,1,1,'2014-03-29 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (80,'YX44CQYZ',1,0,0,0,0,'2014-04-21 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (35,'RND9QRB2',0,0,1,0,1,'2014-05-23 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (45,'VYVP8D4G',0,0,0,0,0,'2014-09-02 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (44,'NHM5PVAG',1,1,1,1,1,'2014-08-10 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (49,'TXXRSTUX',1,0,0,1,1,'2014-07-11 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (99,'TB8CANT2',1,0,0,0,1,'2014-04-23 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (63,'95FWHW5H',1,1,1,0,1,'2014-03-01 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (44,'ND6Z3F7E',0,0,1,0,0,'2014-01-22 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (15,'27T2SERM',0,1,0,0,1,'2014-11-05 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (87,'SP6HF9Q5',1,0,1,0,0,'2014-10-06 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (65,'HDRLV3WL',1,0,0,1,1,'2014-04-09 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (45,'8UHYGG79',0,1,0,0,0,'2014-08-01 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (93,'J93FTBAS',1,0,0,0,1,'2014-08-11 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (77,'K4LBURCW',1,0,0,1,0,'2014-05-01 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (27,'CAEHMFZL',0,1,0,0,0,'2014-03-01 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (57,'9TFR4AHS',0,0,0,1,1,'2014-03-22 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (57,'Y948MB6M',1,0,1,1,0,'2014-08-25 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (99,'4ZDC93QL',1,0,1,1,0,'2014-07-27 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (60,'Q4VY9NW2',0,0,0,1,0,'2014-06-29 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (88,'QCZMU8XN',1,0,1,0,1,'2014-04-27 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (93,'DTV2YSRY',1,0,1,1,1,'2014-02-24 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (74,'37D9RZ6S',1,1,0,1,0,'2014-09-25 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (93,'DKATMK97',1,1,1,1,1,'2014-03-23 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (44,'Y6RJZQRY',1,0,0,0,0,'2014-08-22 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (31,'FTJ37HS3',1,0,1,1,1,'2014-01-27 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (97,'WP3SAWDX',0,1,1,1,0,'2014-02-22 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (64,'3RNR5UAV',0,0,0,0,0,'2014-07-27 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (74,'68HMJY3X',1,0,0,0,0,'2014-06-28 00:00:00');
INSERT INTO TestResults (User_Id,Car_Reg,Alignment,Suspension,Brakes,Exhaust_Emission,Head_Lights,TestDate) VALUES (36,'ZBG6KBZY',0,1,0,0,1,'2014-01-24 00:00:00');

