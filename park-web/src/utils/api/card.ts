import request from '@/utils/request';
import dayjs from 'dayjs';

export function getCard(
    name?: string,
    timeStart?: Date,
    timeEnd?: Date,
    pageSize?: number,
    page?: number
): Promise<Array<Map<string, string>>> {
    return request.get('/api/card/', {
        name: name ? name : "",
        rentable_after: timeStart ? dayjs(timeStart).format('YYYY-MM-DD') : "",
        rentable_before: timeEnd ? dayjs(timeEnd).format('YYYY-MM-DD') : "",
        page_size: pageSize ? pageSize : 50,
        current_page: page ? page - 1 : 0,
    }).then(response => {
        return response.data;
    });
}

export function deleteCard(id: number): Promise<boolean> {
    return request.delete('/api/card/' + id, {}).then(response => {
        return response['code'] === 200;
    });
}

export function createCard(name: string, cost: number, timeStart: Date, timeEnd: Date): Promise<boolean> {
    return request.post('/api/card/', {
        name: name,
        cost: cost,
        rentable_after: dayjs(timeStart).format('YYYY-MM-DD'),
        rentable_before: dayjs(timeEnd).format('YYYY-MM-DD'),
    }).then(response => {
        return response['code'] === 200;
    });
}

export function updateCard(id: number, name: string, cost: number, timeStart: Date, timeEnd: Date): Promise<boolean> {
    return request.put('/api/card/' + id, {
        name: name,
        cost: cost,
        rentable_after: dayjs(timeStart).format('YYYY-MM-DD'),
        rentable_before: dayjs(timeEnd).format('YYYY-MM-DD'),
    }).then(response => {
        return response['code'] === 200;
    });
}