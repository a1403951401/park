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
        start_time: timeStart ? dayjs(timeStart).format('YYYY-MM-DD') : null,
        end_time: timeEnd ? dayjs(timeEnd).format('YYYY-MM-DD') : null,
        page_size: pageSize ? pageSize : 50,
        current_page: page ? page - 1 : 0,
    }).then(response => {
        return response.data;
    });
}
