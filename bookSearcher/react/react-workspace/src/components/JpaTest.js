import React, { useEffect, useState } from 'react';
import { Outlet, Link } from "react-router-dom";
import axios from 'axios';

const JpaPage = () => {
const [testData, setTestData] = useState('');

  useEffect(() => {
    fetchTestData();
  }, []);

  const fetchTestData = async () => {
    try {
      const response = await axios.get('/api/jpaTest/selectJpa');
      const responseData = JSON.stringify(response.data);
      const parsedData = JSON.parse(responseData);
      setTestData(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h1>MyComponent파일</h1>
      <p>/api/jpaTest/selectJpa Link Data: {testData.test}</p>
      <p>/api/jpaTest/selectJpa Link Data: {testData.test2}</p>
      <Link to="/">list</Link>
      <Outlet />
    </div>
  );
};

export default JpaPage;
