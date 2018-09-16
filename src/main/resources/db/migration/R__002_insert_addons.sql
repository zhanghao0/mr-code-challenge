
INSERT INTO addons(id, type, cost) VALUES('75ed96c9-41e3-4436-8596-9479f556a242', 'Call Forwarding', 6) ON CONFLICT DO NOTHING;
INSERT INTO addons(id, type, cost) VALUES('b1677c57-09df-495f-8198-2fb0b9b78a4c', 'Voice Mail', 3) ON CONFLICT DO NOTHING;
INSERT INTO addons(id, type, cost) VALUES('c14a8084-92e6-4b61-a1a4-217f51cd3002', 'Caller ID', 7) ON CONFLICT DO NOTHING;
INSERT INTO addons(id, type, cost) VALUES('e4744163-393e-4e8a-adca-0833c3d1c2d5', 'Roaming', 15) ON CONFLICT DO NOTHING;