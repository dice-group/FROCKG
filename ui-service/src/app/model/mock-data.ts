import {CgData} from './cg-data';

export const GRAPHDATA = {
  'veracityValue': -0.09999999999999998,
  'piecesOfEvidence': [
    {
      'score': 0.5,
      'evidence': [{
        'subject' : 'http://s1.com',
        'property' : 'http://p1.com',
        'object' : 'http://o1.com'
      }],
      'verbalization': 'Verbalization is disabled.',
      'id': 1
    },
    {
      'score': 0.3,
      // tslint:disable-next-line:max-line-length
      'evidence': [{
        'subject' : 'http://a1.com',
        'property' : 'http://rdf.frockg.eu/resource/fdaers/i_f_code',
        'object' : 'http://s1.com'
      }, {
        'subject' : 'http://a1.com',
        'property' : 'http://rdf.frockg.eu/resource/fdaers/i_f_code',
        'object' : 'http://a2.com'
      }, {
        'subject' : 'http://o1.com',
        'property' : 'http://rdf.frockg.eu/resource/fdaers/occupation',
        'object' : 'http://a2.com'
      }],
      'verbalization': 'Verbalization is disabled.',
      'id': 2
    }
  ],
  // tslint:disable-next-line:max-line-length
  'fact': {
    'subject' : 'http://s1.com',
    'property' : 'http://rdf.frockg.eu/resource/fdaers/occupation',
    'object' : 'http://o1.com'
  }
};
