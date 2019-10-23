INSERT INTO directions (dir_id, dir_name, created_at, updated_at) VALUES
	(1, 'CLOCKWISE', '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000'),
	(2, 'ANTICLOCKWISE', '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000');
 
  
INSERT INTO weather_condition_types (wct_id, wct_name, created_at, updated_at) VALUES
	(1, 'DROUGHT', '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000'),
	(2, 'RAINY', '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000'),
	(3, 'OPTIMAL_CONDITIONS', '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000'),
	(4, 'UNDETERMINED', '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000');
  

INSERT INTO polar_coordinates (pco_id, pco_distance, pco_angle, created_at, updated_at) VALUES
	(1, 500, 0, '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000'),
	(2, 2000, 0, '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000'),
	(3, 1000, 0, '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000');
	

INSERT INTO cartesian_coordinates (cco_id, cco_xposition, cco_yposition, created_at, updated_at) VALUES
	(1, 500, 0, '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000'),
	(2, 2000, 0, '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000'),
	(3, 1000, 0, '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000');


INSERT INTO solar_systems (ssy_id, ssy_name, created_at, updated_at)VALUES
	(1, 'SISTEMA VFB', '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000');
	
	
INSERT INTO planets (pla_id, pla_name, pla_displacement, pco_id, cco_id, dir_id, ssy_id, created_at, updated_at) VALUES	
	(1, 'Ferengi', 1, 1, 1, 1, 1, '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000'),
  	(2, 'Betasoide', 3, 2, 2, 1, 1, '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000'),
	(3, 'Vulcano', 5, 3, 3, 2, 1, '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000');


INSERT INTO weather_conditions (wco_id, wco_day, wct_id, ssy_id, created_at, updated_at) VALUES
	(1, -1, 1, 1, '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000'),
	(2, 20000, 1, 1, '2019-10-15 22:00:00.000', '2019-10-15 22:00:00.000');
	
