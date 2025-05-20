--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2 (Debian 17.2-1.pgdg120+1)
-- Dumped by pg_dump version 17.2 (Debian 17.2-1.pgdg120+1)

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
cae6ecd9-0278-4f7a-a30e-8f9802efb1c6	00200a95-81d4-4b5c-aca6-196062bf50ce	2025-05-20 00:28:31.251201	c2a89151-0741-4b3e-b538-0dc125ae9d63	Board despite attention become.
cae6ecd9-0278-4f7a-a30e-8f9802efb1c6	82ea2caa-fdcc-4962-8987-54894b9eee65	2025-05-20 00:28:31.251905	274c0523-9314-439b-bae5-a22b6318c764	Itself better ago rule avoid bit social school.
cae6ecd9-0278-4f7a-a30e-8f9802efb1c6	af5b9c6d-49d6-4edd-b24f-fb761601c824	2025-05-20 00:28:31.252326	c2a89151-0741-4b3e-b538-0dc125ae9d63	At success sit kind.
cae6ecd9-0278-4f7a-a30e-8f9802efb1c6	6cede272-0880-41c7-a892-9469f9345a72	2025-05-20 00:28:31.252747	274c0523-9314-439b-bae5-a22b6318c764	Officer head quickly tough size movement help.
b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b	ffc4e88a-60e5-4ba0-9a13-2b7a939ae8e1	2025-05-20 00:28:31.253131	341870db-737e-429a-9f59-f72b4fad7325	Chair hand trip strong allow without.
b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b	470368af-adcc-4ed8-8d7b-6db8f3aecdea	2025-05-20 00:28:31.253497	1fa464f9-349d-484b-af52-c48b5738691f	Contain region radio edge leader effort.
b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b	5a4a58e2-a797-457c-b4bd-1f356aa8319b	2025-05-20 00:28:31.253953	341870db-737e-429a-9f59-f72b4fad7325	Smile shoulder prove two size.
b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b	860064a6-ff6e-433f-a9df-19f26769879d	2025-05-20 00:28:31.254367	1fa464f9-349d-484b-af52-c48b5738691f	Somebody single answer nearly color.
c2a89151-0741-4b3e-b538-0dc125ae9d63	aa7408ec-cf95-414c-92bd-89bfaea37632	2025-05-20 00:28:31.254963	61d64824-d5bf-4303-99fd-d92d8b1f519d	Together financial series key.
c2a89151-0741-4b3e-b538-0dc125ae9d63	c346ab24-757e-4e7a-b58d-b8cd1147eded	2025-05-20 00:28:31.255546	cae6ecd9-0278-4f7a-a30e-8f9802efb1c6	Theory sure on.
c2a89151-0741-4b3e-b538-0dc125ae9d63	d9c672e5-664d-4c01-b40c-dfbdf6c8d12a	2025-05-20 00:28:31.256113	61d64824-d5bf-4303-99fd-d92d8b1f519d	Pattern them around executive ahead sometimes.
c2a89151-0741-4b3e-b538-0dc125ae9d63	8e961bcc-b6c2-4f70-9b30-e288207c11e6	2025-05-20 00:28:31.256635	cae6ecd9-0278-4f7a-a30e-8f9802efb1c6	Hotel man agreement reveal car mouth size.
e68370e1-327f-437e-a81e-535a8ba7d9db	d976b218-7bda-4b3d-b806-95c068c34459	2025-05-20 00:28:31.257132	61d64824-d5bf-4303-99fd-d92d8b1f519d	Involve relationship whom example so number standard him.
e68370e1-327f-437e-a81e-535a8ba7d9db	ac4898eb-031b-46a8-a946-f691f4310735	2025-05-20 00:28:31.257598	34ca731b-2ac9-4fb3-8436-0feab72c5f5b	Watch thousand town bag break film.
e68370e1-327f-437e-a81e-535a8ba7d9db	39e84196-05c6-4ed5-83fa-1fc46f7ce256	2025-05-20 00:28:31.258025	c1c73d44-e2df-49cc-8803-7a1a9cb3730c	Form over follow base man.
e68370e1-327f-437e-a81e-535a8ba7d9db	97cb206c-65a7-479f-abbb-1a78d814a3ee	2025-05-20 00:28:31.258389	7f4b48e1-ec46-4c12-8c4a-d37fb88935b6	Of kind current finish.
e68370e1-327f-437e-a81e-535a8ba7d9db	2f37cd1a-7aac-4def-b244-0163707635f9	2025-05-20 00:28:31.258808	61d64824-d5bf-4303-99fd-d92d8b1f519d	Position total thought recently bill.
e68370e1-327f-437e-a81e-535a8ba7d9db	b978580c-947d-4e3f-b4ce-b8f83b265f6d	2025-05-20 00:28:31.259185	34ca731b-2ac9-4fb3-8436-0feab72c5f5b	Start police low account.
e68370e1-327f-437e-a81e-535a8ba7d9db	b9e6365e-5b2d-4d7e-8fcc-2bccffad4821	2025-05-20 00:28:31.25957	c1c73d44-e2df-49cc-8803-7a1a9cb3730c	Something young probably sit lay.
e68370e1-327f-437e-a81e-535a8ba7d9db	c66a4fb5-983e-439b-8f06-8104f64cf62f	2025-05-20 00:28:31.259954	7f4b48e1-ec46-4c12-8c4a-d37fb88935b6	Modern training around building forward difficult better.
c1c73d44-e2df-49cc-8803-7a1a9cb3730c	cd858780-796d-430f-baf7-227a9f16cf31	2025-05-20 00:28:31.260401	34ca731b-2ac9-4fb3-8436-0feab72c5f5b	Sea whom soon participant book mind.
c1c73d44-e2df-49cc-8803-7a1a9cb3730c	11740372-8e45-46f5-a00e-690cce940bcb	2025-05-20 00:28:31.260824	e68370e1-327f-437e-a81e-535a8ba7d9db	Involve well drop difficult.
c1c73d44-e2df-49cc-8803-7a1a9cb3730c	f5cfff78-8b92-4347-92d4-881f10939da9	2025-05-20 00:28:31.261203	34ca731b-2ac9-4fb3-8436-0feab72c5f5b	Brother wide buy add only capital general town.
c1c73d44-e2df-49cc-8803-7a1a9cb3730c	21b61dbc-b84d-46fd-993c-47542226570d	2025-05-20 00:28:31.261593	e68370e1-327f-437e-a81e-535a8ba7d9db	Kind focus despite generation.
34ca731b-2ac9-4fb3-8436-0feab72c5f5b	9573291f-3283-45f0-b437-263a3a2b56d7	2025-05-20 00:28:31.261991	c1c73d44-e2df-49cc-8803-7a1a9cb3730c	Growth exist us manager civil try join second.
34ca731b-2ac9-4fb3-8436-0feab72c5f5b	fd679dcb-618e-4d54-b258-9473f8f52d6c	2025-05-20 00:28:31.262338	7f4b48e1-ec46-4c12-8c4a-d37fb88935b6	Hit understand two deep heavy smile.
34ca731b-2ac9-4fb3-8436-0feab72c5f5b	2e0e1fe5-25a5-4916-806f-3f2a127e27f3	2025-05-20 00:28:31.262727	3d106c64-8da6-4cc5-8f0c-249bda5e0f62	After scientist campaign herself else man.
34ca731b-2ac9-4fb3-8436-0feab72c5f5b	2a270de2-abf0-438a-a6ab-f42c733a4fab	2025-05-20 00:28:31.263105	e68370e1-327f-437e-a81e-535a8ba7d9db	Try south tough range common remain.
34ca731b-2ac9-4fb3-8436-0feab72c5f5b	3e293514-e2f7-4982-888d-660eef2f77f0	2025-05-20 00:28:31.263527	c1c73d44-e2df-49cc-8803-7a1a9cb3730c	Way top mean store nearly spend yet.
34ca731b-2ac9-4fb3-8436-0feab72c5f5b	e1bebc29-645c-4926-b11c-a647bd40241e	2025-05-20 00:28:31.264182	7f4b48e1-ec46-4c12-8c4a-d37fb88935b6	Head way table pick collection government.
34ca731b-2ac9-4fb3-8436-0feab72c5f5b	212792cd-c400-4be8-9b5d-e6389cd599c4	2025-05-20 00:28:31.264777	3d106c64-8da6-4cc5-8f0c-249bda5e0f62	Should few account college stay full walk.
34ca731b-2ac9-4fb3-8436-0feab72c5f5b	65f5515d-d163-4f83-a148-88d5df5983c7	2025-05-20 00:28:31.265216	e68370e1-327f-437e-a81e-535a8ba7d9db	Mother employee into business analysis.
61d64824-d5bf-4303-99fd-d92d8b1f519d	d0ff2acd-2c65-45c4-b1ed-13c899851380	2025-05-20 00:28:31.26567	c2a89151-0741-4b3e-b538-0dc125ae9d63	Sure owner address however experience.
61d64824-d5bf-4303-99fd-d92d8b1f519d	8d8ff31e-ff6b-4a28-b94c-42a629afbe72	2025-05-20 00:28:31.266122	341870db-737e-429a-9f59-f72b4fad7325	Worker relationship professor feel artist green simple.
61d64824-d5bf-4303-99fd-d92d8b1f519d	60eda6b6-e76d-4ce9-8bd0-26fec58b4f40	2025-05-20 00:28:31.266505	e68370e1-327f-437e-a81e-535a8ba7d9db	Imagine town simply perform.
61d64824-d5bf-4303-99fd-d92d8b1f519d	49a7487d-0b23-42f9-8754-cf4ca1cb134f	2025-05-20 00:28:31.26691	c2a89151-0741-4b3e-b538-0dc125ae9d63	Night as baby answer issue necessary.
61d64824-d5bf-4303-99fd-d92d8b1f519d	a27f2dab-4986-4ab6-9217-d53cdf617dfc	2025-05-20 00:28:31.267329	341870db-737e-429a-9f59-f72b4fad7325	Green like pretty may.
61d64824-d5bf-4303-99fd-d92d8b1f519d	eb685616-2e03-4e52-95bf-457c9bac8600	2025-05-20 00:28:31.267797	e68370e1-327f-437e-a81e-535a8ba7d9db	Ahead water bank whom.
341870db-737e-429a-9f59-f72b4fad7325	cdb9c248-0222-46fd-bada-a391ec082fd1	2025-05-20 00:28:31.268234	61d64824-d5bf-4303-99fd-d92d8b1f519d	Father show thing town above.
341870db-737e-429a-9f59-f72b4fad7325	82a97e3f-60d3-4620-a152-460f0157c4f6	2025-05-20 00:28:31.268692	aaebbd63-87e3-4cca-ab13-180b4fb62394	Staff example answer perform there dark treatment minute.
341870db-737e-429a-9f59-f72b4fad7325	22cb854b-63f7-4bee-83c8-f60ed6d343c3	2025-05-20 00:28:31.269126	b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b	Resource form receive party.
341870db-737e-429a-9f59-f72b4fad7325	0636e8f3-edf7-4aa0-8b1f-a15299cf4a80	2025-05-20 00:28:31.269543	61d64824-d5bf-4303-99fd-d92d8b1f519d	Follow reach important cold beyond.
341870db-737e-429a-9f59-f72b4fad7325	25d16bc3-2d2c-490c-8a22-86c191030d1e	2025-05-20 00:28:31.269955	aaebbd63-87e3-4cca-ab13-180b4fb62394	Strategy discuss western idea increase.
341870db-737e-429a-9f59-f72b4fad7325	c44a4192-5dbe-4516-83c1-da194f5ca67c	2025-05-20 00:28:31.270358	b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b	Represent wide moment man new shake north office.
3d106c64-8da6-4cc5-8f0c-249bda5e0f62	1ed5f53a-44cb-44aa-a75d-2023e2d87219	2025-05-20 00:28:31.270875	34ca731b-2ac9-4fb3-8436-0feab72c5f5b	Yes spring phone easy reflect summer bit.
3d106c64-8da6-4cc5-8f0c-249bda5e0f62	f481795b-01fa-496f-9932-053110984770	2025-05-20 00:28:31.271364	aaebbd63-87e3-4cca-ab13-180b4fb62394	Door gun control fund.
3d106c64-8da6-4cc5-8f0c-249bda5e0f62	52cb17a0-f29c-4f38-afff-88321f363e81	2025-05-20 00:28:31.271769	1fa464f9-349d-484b-af52-c48b5738691f	Head check must treatment.
3d106c64-8da6-4cc5-8f0c-249bda5e0f62	489e5ac1-2f3b-421d-85f3-bbc0ef3c4cb4	2025-05-20 00:28:31.272134	34ca731b-2ac9-4fb3-8436-0feab72c5f5b	Many policy human indeed else.
3d106c64-8da6-4cc5-8f0c-249bda5e0f62	a4d9f9e9-2ed2-4df8-9d81-5615979a8e3c	2025-05-20 00:28:31.272507	aaebbd63-87e3-4cca-ab13-180b4fb62394	Care color near recently wind give.
3d106c64-8da6-4cc5-8f0c-249bda5e0f62	e6585ad6-1a34-43d7-a191-9334ef597106	2025-05-20 00:28:31.27294	1fa464f9-349d-484b-af52-c48b5738691f	Moment difficult age ten decision weight body.
1fa464f9-349d-484b-af52-c48b5738691f	b99d7e05-cf81-4913-bb8e-3c8dbaedf31f	2025-05-20 00:28:31.273652	274c0523-9314-439b-bae5-a22b6318c764	Skill foot a wife source soon order.
1fa464f9-349d-484b-af52-c48b5738691f	2d78dc8f-c35e-4253-b811-bb81ab34e703	2025-05-20 00:28:31.274224	b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b	Agent half clear according morning simply amount.
1fa464f9-349d-484b-af52-c48b5738691f	f8e5d62e-60ff-451a-9642-998e4e2f3053	2025-05-20 00:28:31.274825	3d106c64-8da6-4cc5-8f0c-249bda5e0f62	Character avoid government arrive.
1fa464f9-349d-484b-af52-c48b5738691f	1102a4ad-5d7b-424e-bb57-06478b2d6a1a	2025-05-20 00:28:31.275272	274c0523-9314-439b-bae5-a22b6318c764	Building stock must moment bar realize.
1fa464f9-349d-484b-af52-c48b5738691f	9bde6951-5a36-466e-951e-a68f93770728	2025-05-20 00:28:31.275802	b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b	Responsibility worker detail growth travel age cell.
1fa464f9-349d-484b-af52-c48b5738691f	58280f99-38f5-4015-8001-57a24bf28c07	2025-05-20 00:28:31.27625	3d106c64-8da6-4cc5-8f0c-249bda5e0f62	Five evidence and hard.
aaebbd63-87e3-4cca-ab13-180b4fb62394	47f95d43-1a8c-4086-8ff3-905691022537	2025-05-20 00:28:31.276643	341870db-737e-429a-9f59-f72b4fad7325	Population level traditional lot they family nice consider.
aaebbd63-87e3-4cca-ab13-180b4fb62394	c5daf6d7-e54a-4569-9865-8f7ab1192461	2025-05-20 00:28:31.277048	3d106c64-8da6-4cc5-8f0c-249bda5e0f62	Speak how nice a clear dog notice.
aaebbd63-87e3-4cca-ab13-180b4fb62394	19a0c066-e7d5-458b-a795-f2cb8bb81353	2025-05-20 00:28:31.277495	341870db-737e-429a-9f59-f72b4fad7325	Pressure rule media rule no none.
aaebbd63-87e3-4cca-ab13-180b4fb62394	c1ab4fe7-2539-4587-945f-d5533bd99193	2025-05-20 00:28:31.277962	3d106c64-8da6-4cc5-8f0c-249bda5e0f62	Own hour manage consumer international.
7f4b48e1-ec46-4c12-8c4a-d37fb88935b6	ef1d8d29-f8d7-4369-af91-88255dfccb3a	2025-05-20 00:28:31.278378	34ca731b-2ac9-4fb3-8436-0feab72c5f5b	Home see billion result these.
7f4b48e1-ec46-4c12-8c4a-d37fb88935b6	89a6e29e-5698-402f-b37f-ef71446455e4	2025-05-20 00:28:31.278809	e68370e1-327f-437e-a81e-535a8ba7d9db	Opportunity nice young matter.
7f4b48e1-ec46-4c12-8c4a-d37fb88935b6	1231188a-617c-4323-9bbd-9525285292df	2025-05-20 00:28:31.279249	34ca731b-2ac9-4fb3-8436-0feab72c5f5b	Finish toward heavy.
7f4b48e1-ec46-4c12-8c4a-d37fb88935b6	3f8b0ac0-5926-4128-8e44-9fb388416d27	2025-05-20 00:28:31.279787	e68370e1-327f-437e-a81e-535a8ba7d9db	Dinner risk what throughout agency off vote.
274c0523-9314-439b-bae5-a22b6318c764	8e8cba24-96c1-4f9e-9a51-d7572e7ce8c0	2025-05-20 00:28:31.280206	cae6ecd9-0278-4f7a-a30e-8f9802efb1c6	Certain direction fish sometimes once.
274c0523-9314-439b-bae5-a22b6318c764	cabe0538-21b5-4857-aabc-22e2b7136659	2025-05-20 00:28:31.280663	1fa464f9-349d-484b-af52-c48b5738691f	Small baby stand oil.
274c0523-9314-439b-bae5-a22b6318c764	f86dc9db-169b-4dd6-a796-7ac8cd9e2a8e	2025-05-20 00:28:31.281253	cae6ecd9-0278-4f7a-a30e-8f9802efb1c6	Form Mrs analysis bill staff however.
274c0523-9314-439b-bae5-a22b6318c764	b6ffd293-1b31-4e42-8e9f-3f5052fd6330	2025-05-20 00:28:31.281651	1fa464f9-349d-484b-af52-c48b5738691f	Head popular continue late letter.
\.


--
-- Data for Name: posts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.posts (id, created_at, text, user_id) FROM stdin;
87fde356-80c5-4202-961d-f0e035a62bc9	2025-05-20 00:28:31.244067	Place marriage very guy yard live give. Region size although. Conference activity goal throw wife. Itself child thus. Rich represent that.	cae6ecd9-0278-4f7a-a30e-8f9802efb1c6
1b184c69-5f1c-4062-90b7-b3cc0155bfc9	2025-05-20 00:28:31.24496	Benefit I behind it crime. Fear town it. Camera southern part kid. Start who friend end. Board help chance seat.	b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b
e2613fef-5204-4cd3-bb9e-4374b858f074	2025-05-20 00:28:31.245557	Three grow learn camera prepare. Five speak process performance better. Source impact since owner. Visit anyone great bad. Environment church collection take seven positive.	c2a89151-0741-4b3e-b538-0dc125ae9d63
8a7edc93-c00d-4841-9cf2-db8b98f3d220	2025-05-20 00:28:31.246136	Into current left skin. Listen certainly alone professional tend. Create early message last six relationship. As cost item draw. Prepare attack here left any.	e68370e1-327f-437e-a81e-535a8ba7d9db
97a374ac-c809-4431-8eb6-3524cb5423d2	2025-05-20 00:28:31.246649	Sort plant no whether bar instead. Car point large painting moment sometimes everyone Republican. Everything reveal range concern. Community record all exactly sometimes miss. Capital professor east admit painting own again.	c1c73d44-e2df-49cc-8803-7a1a9cb3730c
b4994d5b-d5a7-457e-80ce-9f806c26f6e2	2025-05-20 00:28:31.247174	Every know of style great late. Identify feel why. Audience difficult less during. Run than build trade career site. Place quickly option actually partner anyone listen west.	34ca731b-2ac9-4fb3-8436-0feab72c5f5b
a1d27bbe-437f-4e63-9066-e6a745989390	2025-05-20 00:28:31.247695	Fly chance last government research almost. Green receive center. Pass south effect area. Stand magazine argue establish kid world nice produce. Consider play partner behavior hotel across Mr.	61d64824-d5bf-4303-99fd-d92d8b1f519d
63595d5f-562b-4eeb-b9a9-a2a910a3aabe	2025-05-20 00:28:31.248193	Attack prove energy perform public. Hold can shake learn full owner dinner. Property share event establish over. Charge cut education state play. Trouble give note theory sister clear purpose.	341870db-737e-429a-9f59-f72b4fad7325
d02b8ad7-85c9-488e-a765-f53562f61d28	2025-05-20 00:28:31.248756	Operation difficult bad. Concern million garden type cut war stuff kid. While out matter exist easy street. Compare for huge include. Eat would night play born kind course.	3d106c64-8da6-4cc5-8f0c-249bda5e0f62
17dae525-e1e9-4723-8591-8fc4fc50b415	2025-05-20 00:28:31.249249	Race old race fall grow worry economic. Social partner speak doctor produce. Alone recognize provide. Pattern finally meeting race friend structure both. Manage building seat security better official.	1fa464f9-349d-484b-af52-c48b5738691f
69610c22-0ec1-48e1-b977-fe912f1269e6	2025-05-20 00:28:31.249746	Candidate cost beautiful drop. What teacher production student whom agreement key. Similar decide figure trip board fine note. Reason decade agent set would determine floor. There south this political keep tree these attorney.	aaebbd63-87e3-4cca-ab13-180b4fb62394
1802275e-2e90-4245-b55b-891779b8e44c	2025-05-20 00:28:31.250213	Their save hour media week. Strong southern open investment million war. Modern simple possible century sell life. Where I where trip create as attack. Magazine rule subject draw professor.	7f4b48e1-ec46-4c12-8c4a-d37fb88935b6
8c4bde04-930a-445b-97d2-0dbe47c9d61f	2025-05-20 00:28:31.250662	Show strong thousand former city. Position over institution forward day agency skill. Others gas wind successful. Simply answer yet behind dream significant. Seek shoulder result degree.	274c0523-9314-439b-bae5-a22b6318c764
\.


--
-- Data for Name: schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	init schema	SQL	V1__init_schema.sql	-650992819	postgres	2025-05-20 00:27:41.184316	65	t
\.


--
-- Data for Name: user_friends; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_friends (user_id, friend_id) FROM stdin;
cae6ecd9-0278-4f7a-a30e-8f9802efb1c6	274c0523-9314-439b-bae5-a22b6318c764
274c0523-9314-439b-bae5-a22b6318c764	cae6ecd9-0278-4f7a-a30e-8f9802efb1c6
cae6ecd9-0278-4f7a-a30e-8f9802efb1c6	c2a89151-0741-4b3e-b538-0dc125ae9d63
c2a89151-0741-4b3e-b538-0dc125ae9d63	cae6ecd9-0278-4f7a-a30e-8f9802efb1c6
b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b	341870db-737e-429a-9f59-f72b4fad7325
341870db-737e-429a-9f59-f72b4fad7325	b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b
b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b	1fa464f9-349d-484b-af52-c48b5738691f
1fa464f9-349d-484b-af52-c48b5738691f	b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b
c2a89151-0741-4b3e-b538-0dc125ae9d63	61d64824-d5bf-4303-99fd-d92d8b1f519d
61d64824-d5bf-4303-99fd-d92d8b1f519d	c2a89151-0741-4b3e-b538-0dc125ae9d63
e68370e1-327f-437e-a81e-535a8ba7d9db	34ca731b-2ac9-4fb3-8436-0feab72c5f5b
34ca731b-2ac9-4fb3-8436-0feab72c5f5b	e68370e1-327f-437e-a81e-535a8ba7d9db
e68370e1-327f-437e-a81e-535a8ba7d9db	7f4b48e1-ec46-4c12-8c4a-d37fb88935b6
7f4b48e1-ec46-4c12-8c4a-d37fb88935b6	e68370e1-327f-437e-a81e-535a8ba7d9db
c1c73d44-e2df-49cc-8803-7a1a9cb3730c	34ca731b-2ac9-4fb3-8436-0feab72c5f5b
34ca731b-2ac9-4fb3-8436-0feab72c5f5b	c1c73d44-e2df-49cc-8803-7a1a9cb3730c
c1c73d44-e2df-49cc-8803-7a1a9cb3730c	e68370e1-327f-437e-a81e-535a8ba7d9db
e68370e1-327f-437e-a81e-535a8ba7d9db	c1c73d44-e2df-49cc-8803-7a1a9cb3730c
61d64824-d5bf-4303-99fd-d92d8b1f519d	e68370e1-327f-437e-a81e-535a8ba7d9db
e68370e1-327f-437e-a81e-535a8ba7d9db	61d64824-d5bf-4303-99fd-d92d8b1f519d
341870db-737e-429a-9f59-f72b4fad7325	61d64824-d5bf-4303-99fd-d92d8b1f519d
61d64824-d5bf-4303-99fd-d92d8b1f519d	341870db-737e-429a-9f59-f72b4fad7325
3d106c64-8da6-4cc5-8f0c-249bda5e0f62	34ca731b-2ac9-4fb3-8436-0feab72c5f5b
34ca731b-2ac9-4fb3-8436-0feab72c5f5b	3d106c64-8da6-4cc5-8f0c-249bda5e0f62
3d106c64-8da6-4cc5-8f0c-249bda5e0f62	1fa464f9-349d-484b-af52-c48b5738691f
1fa464f9-349d-484b-af52-c48b5738691f	3d106c64-8da6-4cc5-8f0c-249bda5e0f62
aaebbd63-87e3-4cca-ab13-180b4fb62394	341870db-737e-429a-9f59-f72b4fad7325
341870db-737e-429a-9f59-f72b4fad7325	aaebbd63-87e3-4cca-ab13-180b4fb62394
aaebbd63-87e3-4cca-ab13-180b4fb62394	3d106c64-8da6-4cc5-8f0c-249bda5e0f62
3d106c64-8da6-4cc5-8f0c-249bda5e0f62	aaebbd63-87e3-4cca-ab13-180b4fb62394
7f4b48e1-ec46-4c12-8c4a-d37fb88935b6	34ca731b-2ac9-4fb3-8436-0feab72c5f5b
34ca731b-2ac9-4fb3-8436-0feab72c5f5b	7f4b48e1-ec46-4c12-8c4a-d37fb88935b6
274c0523-9314-439b-bae5-a22b6318c764	1fa464f9-349d-484b-af52-c48b5738691f
1fa464f9-349d-484b-af52-c48b5738691f	274c0523-9314-439b-bae5-a22b6318c764
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, biography, birthdate, city, first_name, last_name, password, username) FROM stdin;
cae6ecd9-0278-4f7a-a30e-8f9802efb1c6	Hello, I am Alice!	1990-01-01	New York	Alice	Smith	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	alice_smith
b112f1b1-f5e7-4402-ac56-a16ad0aa6a4b	Hi, I am Bob!	1992-02-02	Los Angeles	Bob	Johnson	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	bob_johnson
c2a89151-0741-4b3e-b538-0dc125ae9d63	Greetings, I am Charlie!	1995-03-03	Chicago	Charlie	Williams	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	charlie_williams
e68370e1-327f-437e-a81e-535a8ba7d9db	Daughter garden last treat maintain minute.	1990-01-01	North Deborahport	Cameron	Phillips	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	vincentwyatt
c1c73d44-e2df-49cc-8803-7a1a9cb3730c	Technology coach leader shake.	1990-01-01	Chadland	Kimberly	Cruz	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	thompsonthomas
34ca731b-2ac9-4fb3-8436-0feab72c5f5b	Hope understand full network.	1990-01-01	New Tony	Yvette	Kramer	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	robert32
61d64824-d5bf-4303-99fd-d92d8b1f519d	Course southern teacher certain.	1990-01-01	Brandyfurt	Larry	Mccullough	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	fluna
341870db-737e-429a-9f59-f72b4fad7325	Sit head write parent identify election manager.	1990-01-01	New Allisonland	Megan	Holt	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	dwilliams
3d106c64-8da6-4cc5-8f0c-249bda5e0f62	Girl case leave oil other network.	1990-01-01	Russoville	Stephanie	Villanueva	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	torreskristen
1fa464f9-349d-484b-af52-c48b5738691f	Economic certain forward agent situation.	1990-01-01	Douglashaven	Jill	Benton	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	troach
aaebbd63-87e3-4cca-ab13-180b4fb62394	Tv thought subject item easy step product.	1990-01-01	Jennabury	Thomas	Hahn	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	michaelrocha
7f4b48e1-ec46-4c12-8c4a-d37fb88935b6	Beyond about tree.	1990-01-01	New Brendamouth	Kayla	Long	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	justindonovan
274c0523-9314-439b-bae5-a22b6318c764	Serious recent soldier send.	1990-01-01	Williamfurt	Richard	Harper	$2a$10$ciLb5FbHRZLMPy7vts.k0umcI3Atg9MEZJjU3wSrrG8G/svbAXaDq	livingstontroy
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

