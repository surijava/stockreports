select * from stockmovingaverage where nsescriptcode='INFY' order by date;

commit;

select count(*) from (SELECT distinct nsescriptcode FROM stockmarket.stocksnsedaywisereport1) as a;

SELECT * FROM stockmarket.fiidiiopeninterest;
SELECT *  FROM stockmarket.stocksnsedaywisereport1 order by date desc;


select * FROM stockmarket.stockpivotel where nsescriptcode='infy';

select * from fiidiidatareport where date ='2023-11-08';

select * from stockpivotel  where nsescriptcode='infy' order by date desc;

select * from stockrsi where nsescriptcode='MGL'  order by date desc;

select *  from bseevents where nsescriptcode 
in (select nsescriptcode from stocknseindexes  where indextype ='Derivaties') order by meetingdate;

select *  from stockmovingaverage order by date desc;

select * from stocknseindexes  where indextype ='Derivaties' order by (LOTSIZE+0);

select * from stocksnsedaywisereport1 where nsescriptcode='AARTIIND' and date between '2023-10-03' and '2023-10-18' order by date;

select distinct nsescriptcode from stocknseindexes  where indextype ='Derivaties'; 

select nsescriptcode,(((closevalue-previousclosevalue)/lastvalue)*100) as changeValue from stocksnsedaywisereport1 
where  nsescriptcode in  (select nsescriptcode from stocknseindexes  where indextype ='Derivaties') 
and date between '2023-11-01' and '2023-11-13' order by changeValue desc;

select * from stocksnsedaywisereport1 where nsescriptcode = 'RECLTD' order by date desc;

select * from stockcorporateevent;

select * from stockmovingaverage where nsescriptcode = 'LTTS';

select * from stockmovingaverage where nsescriptcode in
 (select distinct nsescriptcode from stocknseindexes  where indextype ='Derivaties') and
 ((CAST(10daysaverage as SIGNED) < CAST(25daysaverage as SIGNED)))
  and date='2023-11-13';
 
 select nsescriptcode,10daysaverage, 25daysaverage,(10daysaverage-25daysaverage) as changes from stockmovingaverage where nsescriptcode in
 (select distinct nsescriptcode from stocknseindexes  where indextype ='Derivaties') and (10daysaverage < 25daysaverage)
 and date='2023-11-13' order by changes desc;
 
  select * from stockmovingaverage where 10daysaverage < 25daysaverage and nsescriptcode in
   (select distinct nsescriptcode from stocknseindexes  where indextype ='Derivaties') and 
  date between '2023-11-10' and '2023-11-13' ;
 
 
 SELECT *  FROM stocksnsedaywisereport1 WHERE MONTH(date) =10 AND YEAR(date) = 2023 and nsescriptcode='AARTIIND' order by date;
 

select nsescriptcode, (openvalue-closevalue)  as dojo ,(highvalue - lowvalue) as dojo1 from stocksnsedaywisereport1 
where  nsescriptcode in  (select nsescriptcode from stocknseindexes  where indextype ='Derivaties' and date='2023-10-25')
 and (((openvalue-closevalue) = -1 )) order by dojo;

select * from stockrsi where nsescriptcode in (
select nsescriptcode from stocksnsedaywisereport1 where date='2023-10-30' and series ='BE')
and date='2023-10-30' and rsi>80 order by rsi;

	