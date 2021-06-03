package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should get room list when hotel id is 1"
    request {
        method GET()
        url("/api/hotel/1/rooms/search")
    }
    response {
        body '''\
                [
                    {
                        "id":9,
                        "uuid":"abc",
                        "hotelId":1,
                        "name":"abc",
                        "floor":"4",
                        "roomType":"QUEEN_SUPERIOR_ROOM",
                        "price":"70000",
                        "comments":"abc",
                        "status":"ACTIVE",
                        "updatedDate":null,
                        "createdDate":1619395200.000000000

                    },
                    {
                        "id":10,
                        "uuid":"1",
                        "hotelId":1,
                        "name":"abc",
                        "floor":"4",
                        "roomType":"QUEEN_SUPERIOR_ROOM",
                        "price":"70000",
                        "comments":"abc",
                        "status":"ACTIVE",
                        "updatedDate":null,
                        "createdDate":1619395200.000000000
                    }
                ] 
        '''
        headers {
            contentType applicationJson()
        }
        status 200
    }
}

