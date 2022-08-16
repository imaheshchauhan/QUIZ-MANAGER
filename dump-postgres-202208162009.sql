--
-- PostgreSQL database dump
--

-- Dumped from database version 12.4
-- Dumped by pg_dump version 14.2

-- Started on 2022-08-16 20:09:29

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'WIN1252';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2886 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 37620)
-- Name: option; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.option (
    option_id integer NOT NULL,
    question_id integer,
    option character varying,
    is_right smallint
);


ALTER TABLE public.option OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 37618)
-- Name: option_option_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.option_option_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.option_option_id_seq OWNER TO postgres;

--
-- TOC entry 2887 (class 0 OID 0)
-- Dependencies: 209
-- Name: option_option_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.option_option_id_seq OWNED BY public.option.option_id;


--
-- TOC entry 206 (class 1259 OID 37583)
-- Name: question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.question (
    question_id integer NOT NULL,
    quiz_id integer,
    question character varying,
    image character varying,
    topic character varying,
    difficulty integer,
    que_type character varying
);


ALTER TABLE public.question OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 37581)
-- Name: question_question_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.question_question_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.question_question_id_seq OWNER TO postgres;

--
-- TOC entry 2888 (class 0 OID 0)
-- Dependencies: 205
-- Name: question_question_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.question_question_id_seq OWNED BY public.question.question_id;


--
-- TOC entry 204 (class 1259 OID 37572)
-- Name: quiz; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.quiz (
    quiz_id integer NOT NULL,
    title character varying
);


ALTER TABLE public.quiz OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 37570)
-- Name: quiz_quiz_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.quiz_quiz_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.quiz_quiz_id_seq OWNER TO postgres;

--
-- TOC entry 2889 (class 0 OID 0)
-- Dependencies: 203
-- Name: quiz_quiz_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.quiz_quiz_id_seq OWNED BY public.quiz.quiz_id;


--
-- TOC entry 212 (class 1259 OID 37636)
-- Name: quiz_result; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.quiz_result (
    qr_id integer NOT NULL,
    question_id integer,
    option_id integer,
    is_right smallint,
    result_id integer
);


ALTER TABLE public.quiz_result OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 37634)
-- Name: quiz_result_qr_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.quiz_result_qr_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.quiz_result_qr_id_seq OWNER TO postgres;

--
-- TOC entry 2890 (class 0 OID 0)
-- Dependencies: 211
-- Name: quiz_result_qr_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.quiz_result_qr_id_seq OWNED BY public.quiz_result.qr_id;


--
-- TOC entry 208 (class 1259 OID 37599)
-- Name: result; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.result (
    result_id integer NOT NULL,
    student_id character varying,
    quiz_id integer
);


ALTER TABLE public.result OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 37597)
-- Name: result_result_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.result_result_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.result_result_id_seq OWNER TO postgres;

--
-- TOC entry 2891 (class 0 OID 0)
-- Dependencies: 207
-- Name: result_result_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.result_result_id_seq OWNED BY public.result.result_id;


--
-- TOC entry 202 (class 1259 OID 37562)
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    student_id character varying NOT NULL,
    name character varying,
    password character varying
);


ALTER TABLE public.student OWNER TO postgres;

--
-- TOC entry 2723 (class 2604 OID 37623)
-- Name: option option_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.option ALTER COLUMN option_id SET DEFAULT nextval('public.option_option_id_seq'::regclass);


--
-- TOC entry 2721 (class 2604 OID 37586)
-- Name: question question_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question ALTER COLUMN question_id SET DEFAULT nextval('public.question_question_id_seq'::regclass);


--
-- TOC entry 2720 (class 2604 OID 37575)
-- Name: quiz quiz_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz ALTER COLUMN quiz_id SET DEFAULT nextval('public.quiz_quiz_id_seq'::regclass);


--
-- TOC entry 2724 (class 2604 OID 37639)
-- Name: quiz_result qr_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz_result ALTER COLUMN qr_id SET DEFAULT nextval('public.quiz_result_qr_id_seq'::regclass);


--
-- TOC entry 2722 (class 2604 OID 37602)
-- Name: result result_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.result ALTER COLUMN result_id SET DEFAULT nextval('public.result_result_id_seq'::regclass);


--
-- TOC entry 2878 (class 0 OID 37620)
-- Dependencies: 210
-- Data for Name: option; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.option (option_id, question_id, option, is_right) FROM stdin;
5	19	25	1
7	19	15	0
8	19	14	0
6	19	26	0
17	3	2	0
18	3	4	1
19	3	6	0
20	3	8	0
21	4	8	0
22	4	6	1
23	4	5	0
24	4	4	0
25	5	3	0
26	5	4	0
27	5	6	1
28	5	2	0
29	6	1	0
30	6	15	0
31	6	16	1
32	6	14	0
33	7	13	0
34	7	11	0
35	7	25	1
36	7	28	0
37	8	45	0
38	8	12	0
39	8	25	1
40	8	78	0
\.


--
-- TOC entry 2874 (class 0 OID 37583)
-- Dependencies: 206
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.question (question_id, quiz_id, question, image, topic, difficulty, que_type) FROM stdin;
22	10	what is your name?	nothing	intro,	5	o
20	3	what	nothing	maths,	1	o
16	1	5*5	nothing	maths,	5	o
17	1	6*3	nothing	maths,	2	o
18	1	5*5	nothing	maths,	1	o
19	1	5*5	nothing	maths,	2	m
23	11	2 * 2 = ?	nothing	maths,	5	o
4	1	3+3=?	nothing	reasoning,	3	m
5	1	3+3=?	nothing	maths,logical,	4	m
6	1	4*4=?	nothing	multiplication,	4	m
7	1	5*5	nothing	maths,multiplication,	4	m
8	1	5*5=?	nothing	multiplication,maths,	3	m
9	1	6*6	nothing	maths,	4	o
11	1	9*9	nothing	maths,	5	o
12	1	45*4	nothing	maths,	5	o
13	1	50*50	nothing	maths,	5	o
14	1	8*8	nothing	maths,	5	o
15	1	6*6	nothing	maths,	4	o
3	1	2+2=?	nothing	maths,reasoning	5	m
\.


--
-- TOC entry 2872 (class 0 OID 37572)
-- Dependencies: 204
-- Data for Name: quiz; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.quiz (quiz_id, title) FROM stdin;
1	Test
2	myquiz
3	myquiz
4	myquiz
5	myquiz
6	myquiz
7	myquiz
8	myquiz
9	myquiz
10	myquiz
11	myquiz
\.


--
-- TOC entry 2880 (class 0 OID 37636)
-- Dependencies: 212
-- Data for Name: quiz_result; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.quiz_result (qr_id, question_id, option_id, is_right, result_id) FROM stdin;
\.


--
-- TOC entry 2876 (class 0 OID 37599)
-- Dependencies: 208
-- Data for Name: result; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.result (result_id, student_id, quiz_id) FROM stdin;
\.


--
-- TOC entry 2870 (class 0 OID 37562)
-- Dependencies: 202
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student (student_id, name, password) FROM stdin;
mahesh@epita.fr	Mahesh	mahesh
\.


--
-- TOC entry 2892 (class 0 OID 0)
-- Dependencies: 209
-- Name: option_option_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.option_option_id_seq', 40, true);


--
-- TOC entry 2893 (class 0 OID 0)
-- Dependencies: 205
-- Name: question_question_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.question_question_id_seq', 23, true);


--
-- TOC entry 2894 (class 0 OID 0)
-- Dependencies: 203
-- Name: quiz_quiz_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.quiz_quiz_id_seq', 11, true);


--
-- TOC entry 2895 (class 0 OID 0)
-- Dependencies: 211
-- Name: quiz_result_qr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.quiz_result_qr_id_seq', 1, false);


--
-- TOC entry 2896 (class 0 OID 0)
-- Dependencies: 207
-- Name: result_result_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.result_result_id_seq', 1, false);


--
-- TOC entry 2734 (class 2606 OID 37628)
-- Name: option option_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.option
    ADD CONSTRAINT option_pk PRIMARY KEY (option_id);


--
-- TOC entry 2730 (class 2606 OID 37591)
-- Name: question question_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_pk PRIMARY KEY (question_id);


--
-- TOC entry 2728 (class 2606 OID 37580)
-- Name: quiz quiz_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz
    ADD CONSTRAINT quiz_pk PRIMARY KEY (quiz_id);


--
-- TOC entry 2736 (class 2606 OID 37641)
-- Name: quiz_result quiz_result_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz_result
    ADD CONSTRAINT quiz_result_pk PRIMARY KEY (qr_id);


--
-- TOC entry 2732 (class 2606 OID 37607)
-- Name: result result_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.result
    ADD CONSTRAINT result_pk PRIMARY KEY (result_id);


--
-- TOC entry 2726 (class 2606 OID 37569)
-- Name: student student_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pk PRIMARY KEY (student_id);


--
-- TOC entry 2740 (class 2606 OID 37629)
-- Name: option option_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.option
    ADD CONSTRAINT option_fk FOREIGN KEY (question_id) REFERENCES public.question(question_id);


--
-- TOC entry 2737 (class 2606 OID 37592)
-- Name: question question_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_fk FOREIGN KEY (quiz_id) REFERENCES public.quiz(quiz_id);


--
-- TOC entry 2741 (class 2606 OID 37642)
-- Name: quiz_result quiz_result_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz_result
    ADD CONSTRAINT quiz_result_fk FOREIGN KEY (question_id) REFERENCES public.question(question_id);


--
-- TOC entry 2742 (class 2606 OID 37647)
-- Name: quiz_result quiz_result_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz_result
    ADD CONSTRAINT quiz_result_fk_1 FOREIGN KEY (result_id) REFERENCES public.result(result_id);


--
-- TOC entry 2743 (class 2606 OID 37652)
-- Name: quiz_result quiz_result_fk_2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz_result
    ADD CONSTRAINT quiz_result_fk_2 FOREIGN KEY (option_id) REFERENCES public.option(option_id);


--
-- TOC entry 2738 (class 2606 OID 37608)
-- Name: result result_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.result
    ADD CONSTRAINT result_fk FOREIGN KEY (student_id) REFERENCES public.student(student_id);


--
-- TOC entry 2739 (class 2606 OID 37613)
-- Name: result result_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.result
    ADD CONSTRAINT result_fk_1 FOREIGN KEY (quiz_id) REFERENCES public.quiz(quiz_id);


-- Completed on 2022-08-16 20:09:29

--
-- PostgreSQL database dump complete
--

