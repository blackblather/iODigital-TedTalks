--
-- PostgreSQL database dump
--

-- Dumped from database version 12.22 (Debian 12.22-1.pgdg120+1)
-- Dumped by pg_dump version 16.4 (Ubuntu 16.4-0ubuntu0.24.04.2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE IF EXISTS ONLY public.talks DROP CONSTRAINT IF EXISTS talks_authors_id_fk;
ALTER TABLE IF EXISTS ONLY public.talks DROP CONSTRAINT IF EXISTS talks_pk;
ALTER TABLE IF EXISTS ONLY public.authors DROP CONSTRAINT IF EXISTS authors_pk;
ALTER TABLE IF EXISTS public.talks ALTER COLUMN id DROP DEFAULT;
ALTER TABLE IF EXISTS public.authors ALTER COLUMN id DROP DEFAULT;
DROP SEQUENCE IF EXISTS public.talks_id_seq;
DROP TABLE IF EXISTS public.talks;
DROP SEQUENCE IF EXISTS public.authors_id_seq;
DROP TABLE IF EXISTS public.authors;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA IF NOT EXISTS public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: authors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authors (
                                id integer NOT NULL,
                                name character varying NOT NULL
);


ALTER TABLE public.authors OWNER TO postgres;

--
-- Name: authors_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.authors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.authors_id_seq OWNER TO postgres;

--
-- Name: authors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.authors_id_seq OWNED BY public.authors.id;


--
-- Name: talks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.talks (
                              id integer NOT NULL,
                              author_id integer NOT NULL,
                              title character varying NOT NULL
                                  constraint talks_pk_2
                                      unique,
                              views integer NOT NULL,
                              likes integer NOT NULL,
                              url character varying NOT NULL
                                  constraint talks_pk_3
                                      unique,
                              influence_factor integer NOT NULL,
                              year integer NOT NULL
);


ALTER TABLE public.talks OWNER TO postgres;

--
-- Name: talks_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.talks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.talks_id_seq OWNER TO postgres;

--
-- Name: talks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.talks_id_seq OWNED BY public.talks.id;


--
-- Name: authors id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors ALTER COLUMN id SET DEFAULT nextval('public.authors_id_seq'::regclass);


--
-- Name: talks id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.talks ALTER COLUMN id SET DEFAULT nextval('public.talks_id_seq'::regclass);


--
-- Name: authors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.authors_id_seq', 1, false);


--
-- Name: talks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.talks_id_seq', 1, false);


--
-- Name: authors authors_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_pk PRIMARY KEY (id);


--
-- Name: talks talks_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.talks
    ADD CONSTRAINT talks_pk PRIMARY KEY (id);


--
-- Name: talks talks_authors_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.talks
    ADD CONSTRAINT talks_authors_id_fk FOREIGN KEY (author_id) REFERENCES public.authors(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

