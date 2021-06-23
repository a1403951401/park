import request from '@/utils/request';
import dayjs from 'dayjs';

export function rentCard(id: number): Promise<boolean> {
    return request.post('/api/rent/' + id, {}).then(response => {
        return response['code'] === 200;
    });
}

export function getRemt(): Promise<Array<Map<string, string>>> {
    return request.get('/api/rent/', {}).then(response => {
        return response.data;
    });
}