-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 26 Sty 2023, 19:48
-- Wersja serwera: 10.4.27-MariaDB
-- Wersja PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `car_rental`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `samochody`
--

CREATE TABLE `samochody` (
  `ID_samochodu` int(11) NOT NULL,
  `marka` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `ID_klienta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `samochody`
--

INSERT INTO `samochody` (`ID_samochodu`, `marka`, `model`, `ID_klienta`) VALUES
(1, 'Audi', 'A4', 1),
(2, 'Audi', 'A5', NULL),
(3, 'Audi', 'A6', NULL),
(4, 'Citroen', 'C4', NULL),
(5, 'Fiat', 'Punto', NULL),
(6, 'Fiat', 'Bravo', NULL),
(7, 'Opel', 'Insignia', NULL),
(8, 'Opel', 'Astra', NULL),
(9, 'Porshe', '911', NULL),
(10, 'BMW', 'E36', NULL),
(11, 'Mitsubishi', 'Lancer', NULL),
(12, 'Volkswagen', 'Passat', NULL),
(13, 'Bentley', 'Continental', NULL),
(14, 'Porshe', 'Carrera 911', NULL),
(15, 'Audi', 'A1', NULL),
(16, 'Audi', 'A2', NULL),
(17, 'Audi', 'A3', NULL),
(18, 'Audi', 'A4', NULL),
(19, 'Audi', 'A5', NULL),
(20, 'Audi', 'A6', NULL),
(21, 'Audi', 'A7', NULL),
(22, 'Bentley', 'Continental', NULL),
(23, 'BMW', 'X3', NULL),
(24, 'BMW', 'X4', NULL),
(25, 'BMW', 'X5', NULL),
(26, 'Ferrari', '458', NULL),
(27, 'Ford', 'Edge', NULL),
(28, 'Ford', 'Explorer', NULL),
(29, 'Jaguar', 'XE', NULL),
(30, 'Jaguar', 'XF', NULL),
(31, 'Jaguar', 'XJ', NULL),
(32, 'Lamborghini', 'Urus', NULL),
(33, 'Land Rover', 'Defender', NULL),
(34, 'Mercedes-Benz', 'A', NULL),
(35, 'Mercedes-Benz', 'B', NULL),
(36, 'Mercedes-Benz', 'C', NULL),
(37, 'Mercedes-Benz', 'G', NULL);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `samochody`
--
ALTER TABLE `samochody`
  ADD PRIMARY KEY (`ID_samochodu`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `samochody`
--
ALTER TABLE `samochody`
  MODIFY `ID_samochodu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
