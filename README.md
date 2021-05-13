# motif-discrovery

Description
===========
  This is command-line tool for discovering motifs in a group of related DNA or protein sequences using Gibbs' algorithm.
### Input
  File in FASTA format (.fas, .fasta & etc)
### Output
  Matrix respresenting founded motif:
      A : 0.50        0.30    0.90    0.20    0.60    0.50    0.30    0.20    0.00    0.10
      C : 0.20        0.70    0.00    0.50    0.00    0.00    0.00    0.00    0.40    0.40
      G : 0.20        0.00    0.10    0.00    0.00    0.00    0.10    0.10    0.20    0.50
      T : 0.10        0.00    0.00    0.30    0.40    0.50    0.60    0.70    0.40    0.00
  
  Each element representing the probability of appearing for letter in the row, and column represent a place in motif sequence.

How to build
============
Run <./gradlew build>

How to run
==========
Run <./gradlew run>

Authors:
- Maksim Solomonov
- Danil Safronov
- Anastasia Shemyakinskaya
- Valentin Maksimchuk
- Roman Dolmatov
- Nguyen Hai Yen
