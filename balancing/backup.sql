--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5 (Debian 17.5-1.pgdg120+1)
-- Dumped by pg_dump version 17.5 (Debian 17.5-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messages (
    sender_id uuid NOT NULL,
    id uuid NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    receiver_id uuid NOT NULL,
    text character varying(65535) NOT NULL
);


ALTER TABLE public.messages OWNER TO postgres;

--
-- Name: posts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.posts (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone NOT NULL,
    text character varying(65535) NOT NULL,
    user_id uuid NOT NULL
);


ALTER TABLE public.posts OWNER TO postgres;

--
-- Name: schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.schema_history OWNER TO postgres;

--
-- Name: user_friends; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_friends (
    user_id uuid NOT NULL,
    friend_id uuid NOT NULL
);


ALTER TABLE public.user_friends OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    biography character varying(255),
    birthdate date,
    city character varying(255),
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    password character varying(255),
    username character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.messages (sender_id, id, created_at, receiver_id, text) FROM stdin;
\.


--
-- Data for Name: posts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.posts (id, created_at, text, user_id) FROM stdin;
\.


--
-- Data for Name: schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	init schema	SQL	V1__init_schema.sql	-650992819	postgres	2025-08-06 19:44:42.33463	25	t
\.


--
-- Data for Name: user_friends; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_friends (user_id, friend_id) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, biography, birthdate, city, first_name, last_name, password, username) FROM stdin;
0c04f0b9-5d89-47f5-989f-6ae430e29b53	Hello, I am Alice!	1990-01-01	New York	Alice	Smith	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	alice_smith
5064d562-a4ee-4c4a-bd6b-a968456fbd5b	Hi, I am Bob!	1992-02-02	Los Angeles	Bob	Johnson	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	bob_johnson
805dc7c5-d5a6-441a-9062-aa0ba065eafe	Greetings, I am Charlie!	1995-03-03	Chicago	Charlie	Williams	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	charlie_williams
f5f00cde-527f-48db-b4a9-2df950e0ba73	Admit whatever past teach cup.	1990-01-01	Reynoldsberg	Brittany	Moore	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	ellenaustin
bbc2e1a5-d7c1-4774-bab7-dba4fec1bf3b	Whole apply beat prove.	1990-01-01	South Matthew	Shelley	Adams	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	sanchezjennifer
cf41aecb-dac7-49f7-99f6-ea7d4cd3136f	Man the ground second letter.	1990-01-01	West Diane	Zachary	Valencia	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	jennifercunningham
d665c2aa-3ed6-47a8-af08-b286b04b3177	Finish part occur area high.	1990-01-01	North Jonathan	Stacy	Chan	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	acollins
2b82ace2-b89b-45a2-932a-e6dcbef68d0a	Dark little future world.	1990-01-01	Patriciaside	Lisa	Luna	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	ssosa
646a098b-8d40-4962-bc2b-61091e80c473	Study main success store tell.	1990-01-01	Carolshire	Danielle	Mcmillan	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	torreskelly
1d717b04-3445-4270-810f-8ffe00feab87	Argue I key blood.	1990-01-01	South Kyleside	Roberto	Roberts	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	williamstraci
c77a6e12-7183-43e2-ac16-73abe9dbb311	Eat born what important television artist site.	1990-01-01	Jeffreyhaven	Wyatt	Campos	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	christinasanchez
f69b4726-7b2d-4bbc-aa02-7e9272099a2a	Everyone any thousand thank.	1990-01-01	Andreahaven	Michael	Jones	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	tommy12
723fb416-bbac-4e73-b529-133af2bf83a3	Pay score development church.	1990-01-01	Booneberg	Robert	Jimenez	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	blakebrandi
b8ed77d4-4285-4a24-aa25-ff8024efb973	Class relate serve cup dream finish.	1990-01-01	Amandachester	Alexandra	Mosley	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	patty96
82af32f8-cabf-41d4-9ef8-214b096b0299	Remember word direction agreement affect evidence more structure.	1990-01-01	North Joseburgh	Amanda	Carr	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	slynch
06b49e8b-884f-4e42-9bee-0df7db3f9085	Service allow stay garden range work six most.	1990-01-01	Aaronfort	Alexis	Mcmillan	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	christyharrison
18d22258-a282-452b-b8bd-63e84550412c	Fish exist similar ever.	1990-01-01	Reedburgh	Casey	Miller	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	jonesnathaniel
ff59561c-b878-497e-ab84-b06460b412cd	Alone so free eat.	1990-01-01	Ericatown	Crystal	Jones	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	ymccormick
53a2d75d-0a16-4d15-befa-7d4cf109ecd6	Tough choose share.	1990-01-01	New Billy	Carlos	Campbell	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	bakerleah
762eaee5-94aa-43d9-ae83-539bf5a545f1	Debate for drug result better sport.	1990-01-01	Lake Laurentown	Lauren	Lewis	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	garzakevin
e35effb8-8988-462c-b87b-79bc726c506d	We pattern late turn simply budget hit.	1990-01-01	North Regina	Xavier	Neal	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	colliermichael
302b3a6f-01a9-4357-8185-a9a675f97894	After smile case game.	1990-01-01	Port Jeffreyhaven	Cameron	Finley	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	william82
46233f7b-88b1-42e4-86b8-43dbe2761180	Heart member more player center paper.	1990-01-01	Millermouth	Regina	Osborne	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	samantha54
93d7b6db-cbe0-4b0b-86eb-201d7d29c33a	Will including resource option girl.	1990-01-01	Port Sean	John	Fox	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	nlin
6b5a9e08-4fd6-4cc1-a330-9f71872b818b	Some he process theory good general.	1990-01-01	West Jaimetown	Mark	Oneal	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	gilbertelizabeth
bca6b0e7-3f9c-4fec-b5a6-6cbb93935d6f	Born attention remember detail level early general tree.	1990-01-01	East Natasha	Tammy	Delgado	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	lucasli
bd7db5c2-c0c8-475b-9a89-f8777231a1f2	Manage lay wall debate attorney cost.	1990-01-01	Cuevasstad	Julie	Bailey	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	danielsmith
e8b4389f-1d2f-4ca4-916e-2207dfb68138	Image health effect.	1990-01-01	North Aaronburgh	Steven	Wright	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	gwyatt
b7a7f897-7667-4298-9258-6f5340a5eb92	Through impact garden list meet.	1990-01-01	New Dillon	David	Clark	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	paulgraves
6d0b4e99-9281-48b5-859a-1c3a17ae5947	Third owner argue.	1990-01-01	Port Annefurt	Rhonda	Castillo	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	amyhobbs
31ee6c66-bf6b-4a54-acf9-3ebc8ec998d1	Toward teacher avoid require.	1990-01-01	Jasonstad	Benjamin	Johns	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	mary46
89d09457-b81e-4ef1-b498-be8b64f4466a	Have head old or increase pretty more.	1990-01-01	Raymondland	Donna	Smith	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	meganparsons
7b016f7e-f1fb-4312-9fa5-41eadbcb3b9c	Son position sing either accept focus.	1990-01-01	North Marcus	Cory	Sanchez	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	mark96
e5cb6214-6e82-4c0b-9b34-58b1b92c5ce7	Board majority bring why.	1990-01-01	Harttown	Kevin	Greene	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	wrightsarah
63b4f35c-9b0b-4087-b272-4d6664701d59	Democratic note coach return speech.	1990-01-01	Port Katherine	Karen	Davis	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	wjohnson
4c777218-7fab-47e7-b712-ebf9b0bcefb4	Debate have employee spring boy after degree.	1990-01-01	Port Shawnhaven	Valerie	Riggs	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	nlee
75754a3c-78ab-409a-96ca-f3e20fe92460	Those marriage exactly factor back federal.	1990-01-01	Lake Kristinechester	Kenneth	Avila	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	bdaniel
e7047b17-efcb-42e0-b1e2-cba9ed2001f2	With keep eat month begin.	1990-01-01	East Katrinaville	Scott	Evans	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	jacquelineparker
fd683dff-713d-4669-9572-11ec55062d35	Tend fine protect modern pressure purpose bill.	1990-01-01	Sarafort	Meghan	Sanders	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	ritterkathleen
cfaf52e1-6a08-4c8e-88ac-240c82972725	Than car green fire.	1990-01-01	Lake Dawnton	Claudia	Cook	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	stacey14
a3b3ab02-0f16-44bf-b700-b7746a8deec1	Remain quite should week school another.	1990-01-01	Watsonborough	James	Valdez	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	vharvey
6fe040d2-6b4b-441c-920c-e56fd2881aef	Painting star attorney.	1990-01-01	Christinemouth	Austin	House	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	aaron18
3209a708-64b1-4676-8f2d-07389bb1bf17	Group dinner deep sit.	1990-01-01	South Angelica	Michael	Price	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	lrogers
650f4a6d-636d-41ef-9190-536cf5c77a00	Play care want east.	1990-01-01	New Frederickside	Mary	Baker	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	dkeith
b203b3fe-86fb-49a1-b9f4-0af628995194	Dark account major cause season.	1990-01-01	Rebeccaport	Jason	Hensley	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	tammy51
408bb9e6-e8c9-4a86-9b51-bbdb9a01713b	Performance cause its mother.	1990-01-01	East Lisa	Corey	Green	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	amandasmith
da742b71-61ac-4950-9b79-dc44f90b4583	Board wrong across.	1990-01-01	Russellville	Vanessa	Wilson	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	ikim
41eae608-bdf3-46eb-b234-7db68f52c758	Cultural contain left executive.	1990-01-01	Meghanfurt	John	Weaver	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	idavis
f9e52d03-3d43-4c7e-8998-b4d645b59142	Argue cup he others green explain.	1990-01-01	New Jesseborough	Kenneth	Matthews	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	ronald72
5f78ed5e-ae33-48de-9d9c-0307567dbc47	Manage might too.	1990-01-01	Thomasfort	Katherine	Miller	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	ehenderson
d26d6b9c-14e7-47f7-8ccd-85312240605d	Morning find later certainly.	1990-01-01	West Melinda	Erika	Lopez	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	kristin23
551e2b7a-30d4-423d-abe2-0893a030a106	Thus see plant town north crime.	1990-01-01	Lake Jessica	Ethan	Velazquez	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	cdavis
2d003fed-a5ff-4efb-b773-4d86cbe430b6	Exactly politics ten capital must out.	1990-01-01	Garyhaven	Willie	Phillips	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	vfoster
c2e9627b-3cb9-4b1c-818a-048522841b82	Hit decide eat environment later short meeting.	1990-01-01	Reedshire	Timothy	George	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	vburns
39cd9c84-b68e-4635-bc97-5ef463805d87	Call series could and work worker money green.	1990-01-01	Port Dustintown	Jonathan	Thompson	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	karencarey
82add53d-77e4-4231-bdeb-f308ed354f65	Eat carry care college small fine arm society.	1990-01-01	Carpentermouth	Laura	Thompson	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	jordan91
0090502a-3d2f-4bf2-ada8-578bcd519854	Whose almost toward mean financial decade.	1990-01-01	Walshville	Mary	Ray	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	kathryn34
253c8965-a884-4ac1-b516-cb8a39d37e7c	Enter federal send family coach.	1990-01-01	Brownview	Jonathan	Owens	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	robert45
3a602d10-8c68-469f-ac98-0d9e2f4445ce	Event claim show just building allow.	1990-01-01	Mooretown	Megan	Booth	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	stephenmcdonald
c8c46cb2-9250-4100-b470-ab4a55158ebd	Market agent Mr.	1990-01-01	Georgeshire	Kathy	Hill	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	melindasimmons
395447ae-f18e-49c1-9eef-45f71cd47df5	Reduce shake add outside out thing.	1990-01-01	Staffordmouth	Nicholas	Martin	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	thompsonrobert
0ef1d170-a50d-4403-a384-a733af9bff23	Idea save store if beautiful force.	1990-01-01	Stephenfurt	Daniel	Mcdonald	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	paul69
7434f4c0-d586-410d-ab6f-8fdfcdcde35f	Nation plant since sit.	1990-01-01	Edwardport	Victor	Gray	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	sarahlee
46f20c8f-007a-4054-9197-299034e037e8	Sing it family including hand.	1990-01-01	Port John	Alexander	Sutton	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	michellemays
1c51d7ac-0238-4ad4-a247-2a21383ca987	Appear institution help order reflect.	1990-01-01	Jonathanview	Kevin	Shaffer	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	sgarcia
564dd97f-baa8-4303-a2dd-7d71d440c506	Wind how ready ok fire in.	1990-01-01	Walkerberg	Carolyn	Kidd	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	lstephens
84996076-91db-4104-9537-c000aa50496e	Animal teach would feel admit use effect.	1990-01-01	West Johnbury	Megan	Mack	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	bhill
f2c7ad77-defe-44e8-a1a8-85651784f868	Cover probably word trip rich finally husband.	1990-01-01	Sarahberg	Melissa	Bartlett	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	robert89
b3bb1171-0b58-4e9e-ab6b-c00cc7580656	Executive contain summer general small.	1990-01-01	Thompsonbury	Melissa	Davis	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	dalewhite
640397d6-cc49-40cc-bd63-f248e4ea148f	Reality truth gun better.	1990-01-01	Port Heidi	Tammie	Stone	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	candacewilkinson
e842a68a-854e-4665-b329-969f181ec6a7	Age will give.	1990-01-01	South Kimberly	Kaitlyn	Garcia	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	wchan
5c54e3ad-6222-443f-9cc8-11058703e357	Dark hold woman concern best note conference.	1990-01-01	Rogersville	Jeffrey	Wiley	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	denise13
d77a51bd-c90b-44de-bdba-15252552217a	Respond reduce after light cut little building.	1990-01-01	North Miguelchester	Melinda	Yates	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	fsimmons
4b373ab4-39a4-4cb9-bd2c-0d7d228f5c07	Blue evidence of not item sister statement.	1990-01-01	West Gabriel	Matthew	Thompson	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	gibsonkatherine
ac3d19c0-3ce3-4d14-abd9-cea6c2bc47f4	Meeting suddenly board according.	1990-01-01	Danielville	Austin	Perez	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	xnorman
0ed05d38-d915-4cb6-a4b5-bb20ab819206	Protect court wife our result place.	1990-01-01	South Gregory	Ryan	Lopez	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	yrubio
46c6426c-9fcb-45dc-ac8e-2eb4b9a58b79	Arm store significant old research.	1990-01-01	Ashleytown	Troy	Copeland	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	denise26
af2a3762-e68c-44ef-bb0b-d0ab65c8da9c	Return edge but place.	1990-01-01	East Erinport	Kevin	Sims	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	zacharyclark
0a78d531-9770-4712-9a27-a62593c21db9	City born although product notice despite.	1990-01-01	Daviston	Benjamin	Martin	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	randy74
dfaefb86-c9f3-4162-abcf-1ac012b0045c	Begin I specific key.	1990-01-01	Danielland	Christopher	Castro	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	hulldana
837aead7-f574-4e45-b219-3522dc62da9b	Half brother term home option.	1990-01-01	Schwartzville	Stacy	Sanchez	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	ijones
3999d3f1-93c4-451a-b51d-44bbf3017539	My best itself herself first nice turn guess.	1990-01-01	Rebeccaburgh	Tamara	Tran	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	fwang
b4a2061d-dbc1-40a6-8325-e1f714cae627	Management let believe later increase.	1990-01-01	North Ryanbury	Keith	Miller	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	colemansarah
9563e4b8-0826-4c97-8da5-5a1a80d96d33	Piece toward interest century.	1990-01-01	West Lynnstad	Tracy	Hughes	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	gabriellaward
8a882584-e49a-448e-afb1-bec85437405c	Guy main later speak remember.	1990-01-01	Carrieville	Scott	Miller	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	lbarrett
1679615f-2e6f-42c8-971f-50f60457bdd0	Own between buy fight.	1990-01-01	Stewartstad	Tyler	Davis	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	awalker
bf0d1f23-9bbe-48f3-9d22-4846990d7221	Structure catch yourself window American couple social.	1990-01-01	Lake Brandon	Timothy	Powers	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	dawn25
e2e88c38-5c32-4e84-ba47-78fdf12d0e31	Anyone discussion crime fact pay still.	1990-01-01	Danamouth	Melissa	Wright	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	lisafoster
92964db1-ee33-498f-bee9-56a65dcadfda	Ahead bad sit international keep different would.	1990-01-01	Lake Jenniferville	Donna	Howard	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	hartalexander
c061810c-93ce-4d77-af0c-8ae307bba873	Film later simply mind understand growth.	1990-01-01	Port Michaelaland	Stephanie	Gibson	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	meaton
cedaed0e-57bf-4fb9-a37a-98d75784328d	Happen down particular color miss.	1990-01-01	Jamesport	Tammy	Francis	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	robbinsjonathan
bfb7edc0-c96d-4f60-993f-3e9693c54d7c	Book simply plant space forget.	1990-01-01	Craigton	Kristin	Jackson	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	twest
d19e5503-7e51-4405-96cd-5ab2904ffa7c	Anything prevent his TV sport.	1990-01-01	Lake Krista	Anthony	Mendez	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	ashley40
bc404004-36c0-40c5-8c4e-25a14e29f64d	Administration kid almost institution wrong sport rather cause.	1990-01-01	East Joshuaborough	Michael	Lopez	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	rita81
857ec3ff-fb22-4143-8b72-a69b90c80c4d	One ago amount scene break.	1990-01-01	West Donaldborough	Bryan	Alexander	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	leslieaguirre
24f93d22-4918-4e92-9673-d2e16bc52185	South ever tree.	1990-01-01	Lake Gregory	Dennis	Johnson	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	hatfieldkrystal
f84080b1-f935-4d7b-a85c-0a2aa6b1e734	Agency stage machine through chance glass.	1990-01-01	South William	Samuel	Graves	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	xgordon
f015d170-8e53-47c1-92e2-d42b004979e3	Course why democratic set stage high box truth.	1990-01-01	Sotomouth	Shane	Chapman	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	donaldgillespie
a3e017a1-d9a4-4c28-87f7-be1c94a18db8	Establish social view true attack particularly.	1990-01-01	Nelsonbury	Garrett	Petersen	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	ilewis
9f068476-ac60-4490-9a93-6c22375bde80	Radio take fine forward street father leave.	1990-01-01	Alejandrofurt	Steven	Lee	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	todd78
59720350-3adb-4da5-b53f-3515945abd4f	Organization off turn ten can never.	1990-01-01	West Dustinland	Shannon	Gray	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	walldavid
1c68f501-41a6-4578-b53f-13f6d1127025	Two prove strategy sound late show free.	1990-01-01	Turnerton	Kyle	Hammond	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	charles10
5d967eca-428c-4497-b506-d10a88ffbbb0	Hotel herself create feel hear middle.	1990-01-01	Port Stephaniefurt	Richard	Davis	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	michael06
\.


--
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id, sender_id);


--
-- Name: posts posts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_pkey PRIMARY KEY (id);


--
-- Name: schema_history schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.schema_history
    ADD CONSTRAINT schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: user_friends user_friends_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_friends
    ADD CONSTRAINT user_friends_pkey PRIMARY KEY (user_id, friend_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: idx_receiver_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX idx_receiver_id ON public.messages USING btree (receiver_id);


--
-- Name: schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX schema_history_s_idx ON public.schema_history USING btree (success);


--
-- Name: posts fk_post_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT fk_post_user FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: user_friends fk_user_friends_friend; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_friends
    ADD CONSTRAINT fk_user_friends_friend FOREIGN KEY (friend_id) REFERENCES public.users(id);


--
-- Name: user_friends fk_user_friends_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_friends
    ADD CONSTRAINT fk_user_friends_user FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

