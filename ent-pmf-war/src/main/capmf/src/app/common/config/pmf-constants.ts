export const dataConstants = {
  'view': {
    'landingPage': {
      'description': {
        'description_header': 'The Provider Maintenance Form (PMF) is used to request changes to existing practice profiles of California physicians, practitioners, professionals and ancillary providers with Anthem Blue Cross.',
        'description_0': 'First time users should view all Informational Tool Tips ',
        'description_0_1': ' to ensure the form is submitted accurately.',
        'description_1': 'Change requests should be submitted by the provider, the practice manager or a designated person of authority.',
        'description_2': 'As a general rule, a minimum of 30 days advance notice of a provider demographic and/or practice change is required. Refer to the requirements in your Provider Agreement.',
        'description_3': 'Certain changes may be assigned a future effective date.',
        'description_4': 'Contractual guidelines may supersede the requested effective date of requests.',
        'description_5': 'For change(s) that require submission of an updated IRS Form, W9, or other documentation, attach them to the form in-line prior to submitting.'
      },
      'modal': {
        'id': 'none',
        'modalType': 'none',
        'title': '',
        'hideClose': true,
        'content': 'This action will clear all Selected Updates previously chosen in this form.'
      }
    },
    'selectUpdates': {
      'headers': {
        'header_main': 'Select all items you would like to update.',
        'header_sub': 'NOTE: Changes for multiple providers must be submitted separately.'
      }
    },
    'generalInfo': {
      'headers': {
        'header_main': 'General Information',
        'header_sub': 'Changes for multiple providers must be submitted separately.',
        'header_indi_sub': 'Individual Provider Details',
        'header_org_sub': 'Organization Details'
      },
      'labels': {
        'effective_date': 'EFFECTIVE DATE',
        'affected_provider': 'AFFECTED PROVIDER DETAILS',
        'practice_office': 'PRACTICE OFFICE LOCATION',
        'contact_information': 'CONTACT INFORMATION',
        'practice_details': 'PRACTICE DETAILS'
      },
      'placeholder': {
        'indi_npi': 'NATIONAL PROVIDER IDENTIFIER (NPI)',
        'last_name': 'LAST NAME',
        'first_name': 'FIRST NAME',
        'middle_name': 'MIDDLE NAME',
        'indi_license_number': 'PROFESSIONAL LICENSE NUMBER',
        'street_address_1': 'STREET ADDRESS 1',
        'street_address_2': 'STREET ADDRESS 2',
        'city': 'CITY',
        'zip': 'ZIP CODE',
        'email_address': 'EMAIL ADDRESS',
        'phone_number': 'PHONE NUMBER',
        'tin': 'PRACTICE TAX IDENTIFICATION NUMBER (TIN)',
        'practice_name': 'PRACTICE NAME',
        'org_license_number': 'ORGANIZATION LICENSE NUMBER',
        'org_npi': 'ORGANIZATION NATIONAL PROVIDER IDENTIFIER (NPI)'
      }
    },
    'commonView': {
      'select_titles': [
        'MD',
        'Mr.',
        'Mrs.',
        'Miss'
      ],
      'select_suffix': [
        'Jr.',
        'Sr.',
        'I',
        'II'
      ],
      'select_country_code': [
        '+1',
        '+2',
        '91'
      ],
      'select_state': [
        'AL',
        'AR',
        'AK',
        'AZ',
        'CT',
        'CA',
        'CO',
        'DE',
        'GA',
        'HI',
        'KY'
      ]
    },
    'selectUpdateTypesView': {
      'emailAddress': {
        'labels': {
          'address': 'Address',
          'county': 'County',
          'primary_email': 'Primary Email Address'
        },
        'headers': {
          'email_panel': 'Email Address'
        },
        'placeholder': {
          'email_address': 'info@whc.com'
        }
      },
      'npi': {
        'individual_npi': {
          'labels': {
            'npi_info_message': 'The NPI entered here will replace the NPI entered in General Information.'
          }
        },
        'organization_npi': {
          'labels': {
            'npi_info_message_add': 'The NPI entered here will become an additional NPI on the organization\'s record.',
            'npi_info_message_update': 'The NPI entered here will replace the NPI entered in General Information.'
          }
        }
      },
      'speciality': {
        'labels': {
          'request_type_question': 'What type of specialty request is this?',
          'new_request': 'Add New',
          'update_request': 'Change Existing',
          'provider_type': 'Provider Type',
          'primary_speciality': 'Primary Specialty',
          'additional_speciality': 'Additional Specialty',
          'pcp_question': 'Are you acting as a Primary Care Physician at this location?',
          'pcp_yes': 'Yes',
          'pcp_no': 'No'
        }
      },
      'taxIdOwnership': {
        'labels': {
          'instruction_one': 'Please enter your Previous Tax Identification Number in General Information.',
          'instruction_two': 'To make changes to General Information, utilize the navigation links:',
          'instruction_links': 'Back to Select Updates Page > Back to General Information Page',
          'disclaimer': 'Your new, updated Tax Identification Number should be entered here.',
          'information': 'An updated W-9 form is required to update your Tax Identification Number.',
          'new_tax_id': 'New Tax Identification Number',
          'individual_npi': 'Individual NPI',
          'organization_npi': 'Organization NPI'
        },
        'formElements': {
          'faxEmailCheckBox': {
            'id': 'faxEmail',
            'label': 'I plan to fax, mail, or email my W9',
            'name': 'faxEmail'
          }
        }
      },
      'organizationName': {
        'labels': {
          'information': 'An updated W-9 form is required to update your Organization Name.'
        }
      },
      'networkParticipation': {
        'labels': {
          'request_type_question': 'Are you adding or changing your network participation?',
          'network': 'List of Networks / Lines of Business',
          'network_info_message': 'This is a request for action and not a guarantee of participation or notice of termination. Subject to review and approval by a contract manager. Please refer to the termination clause in your Provider Agreement for additional requirements.'
        }
      },
      'terminationPPA': {
        'labels': {
          'information_message': 'This is a request for action and not a notice of termination. Subject to review and approval by a contract manager. Please refer to the termination clause in your Provider Agreement for additional requirements.',
          'termination_date': 'Termination Date',
          'termination_reason': 'Termination Reason',
          'managedPCPQuestion': 'Were you acting as a Primary Care Physician at this location?',
          'transferPatients': 'Transfer Of Patients',
          'firstName': 'First Name',
          'lastName': 'Last Name',
          'address1': 'Address Line 1',
          'address2': 'Address Line 2',
          'city': 'City',
          'state': 'State',
          'county': 'County',
          'zip': 'Zip',
          'specialty': 'Specialty'
        }
      },
      'hospitalPrivilege': {
        'labels': {
          'hospitalPrivilege': 'Hospital Privilege',
          'affiliationName': 'Name of Affiliation'
        }
      },
      'languagesSpoken': {
        'labels': {
          'individual_languageSpoken': 'Language Spoken',
          'organization_languageSpoken': 'Language Spoken By Staff'
        }
      },
      'operationHours': {
        'labels': {
          'disclaimer': 'Days and Hours of Operation will apply to the address entered in General Information.'
        }
      },
      'unsolicitedRoster': {
        'information_message': 'If you have been authorized to submit a list of provider updates, please enter the number of providers impacted, expand the attachments section below and upload the list.',
        'numberOfProvider': 'Number of Providers Impacted',
        'toggleQuestion': 'I Am a Delegated Credentialing Entity.'
      },
      'telehealth': {
        'labels': {
          'telehealthIndicator': 'Do you provide Telehealth Services at this location? (If marked yes, Telehealth will be displayed in the directory.)'
        }
      },
      'ambulance': {
        'labels': {
          'ambulanceIndicator': 'Are you a publicly funded ambulance services?'
        }
      }
    }
  }
};
